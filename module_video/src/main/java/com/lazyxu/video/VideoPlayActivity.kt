package com.lazyxu.video

import android.view.View
import android.widget.FrameLayout

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_common.constant.Constants
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.RenderersFactory
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView.SHOW_BUFFERING_NEVER
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.FileDataSource
import com.google.android.exoplayer2.upstream.cache.Cache
import com.google.android.exoplayer2.upstream.cache.CacheDataSink
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbVmActivity
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.layoutmanager.OnViewPagerListener
import com.lazyxu.base.utils.layoutmanager.PagerLayoutManager
import com.lazyxu.lib_database.entity.VideoEntity
import com.lazyxu.video.adapter.VideoAdapter
import com.lazyxu.video.databinding.ActivityPlayVideoBinding
import com.lazyxu.video.R
/**
 * User:Lazy_xu
 * Date:2024/05/29
 * Description:仿抖音上下滑动播放页
 * FIXME
 */
@Route(path = ARouterPath.Video.PLAY)
class VideoPlayActivity : BaseVbVmActivity<ActivityPlayVideoBinding, VideoViewModel>() {
    @Autowired(name = Constants.KEY_VIDEO_PLAY_LIST)
    @JvmField
    var mData: ArrayList<VideoEntity>? = null
    private val videoAdapter by lazy {
        VideoAdapter()
    }
    private var mExoPlayer: ExoPlayer? = null
    private var mPlayView: StyledPlayerView? = null

    //缓存对象
    private lateinit var mCache: Cache

    //媒体资源加载工厂类
    private lateinit var mMediaSource: MediaSource.Factory
    override fun createObserver() {

    }

    override fun initView() {
        initPlayer()
        initRecyclerView()
    }

    override fun initClicks() {
        mViewBinding.tbTitle.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initRecyclerView() {
        val manager = PagerLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        manager.setOnViewPagerListener(onScrollPagerListener)
        mViewBinding.rvPlay.apply {
            layoutManager = manager
            adapter = videoAdapter
        }
        if (mData.isNullOrEmpty()) {
            mViewBinding.refreshBest.isRefreshing = false
        } else {
            mViewBinding.refreshBest.isRefreshing = true
        }
        mViewBinding.refreshBest.setOnRefreshListener {
        }
        mViewBinding.refreshBest.setColorSchemeResources(
            com.lazyxu.base.R.color.colorAccent,
            com.lazyxu.base.R.color.colorAccent,
            com.lazyxu.base.R.color.colorAccent
        )
    }

    override fun onResume() {
        super.onResume()
        if (mExoPlayer?.isPlaying == false) {
            mExoPlayer?.playWhenReady = true
        }
    }

    override fun onPause() {
        super.onPause()
        if (mExoPlayer?.isPlaying == true) {
            mExoPlayer?.playWhenReady = false
        }
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    private fun releasePlayer() {
        //清理ExoPlayer资源
        mExoPlayer?.apply {
            playWhenReady = false
            stop()
            release()
            removeListener(playerListener)
        }
        mExoPlayer = null
        //是否播放器资源
        mPlayView?.adViewGroup?.removeAllViews()
        mPlayView?.player = null
        mPlayView = null
        //清除动画
//        mRotateNoteView?.stopAnim()
//        mRotateNoteView = null
        //清理缓存
        mCache.release()
    }

    /**
     * 滑动监听
     */
    private val onScrollPagerListener = object : OnViewPagerListener {


        override fun onPageRelease(isNext: Boolean, position: Int, view: View?) {
            LogUtils.d("VideoTag", "onPageRelease===$isNext | $position")
        }

        override fun onPageSelected(isNext: Boolean, position: Int, view: View?) {
            LogUtils.d("VideoTag", "onPageSelected===$position,isNext=$isNext")
            startPlay(isNext, position, view)
        }

        override fun onPreLoadMore(position: Int, view: View?) {
            LogUtils.d("VideoTag", "onLoadMore===$position ")
            loadMore()
        }
    }

    //当前播放位置
    private var mPlayingPosition = -1
    private fun startPlay(isnext: Boolean, position: Int, view: View?) {
        if (view == null || position < 0 || position >= videoAdapter.data.size || position == mPlayingPosition) return
        mPlayingPosition = position
        val item = videoAdapter.getItem(position)
        if (item.play_url.isEmpty()) return
        //如果父容器不等于this,则把playView添加进去
        //parent如果不为空则是被添加到别的容器中了，需要移除
        (mPlayView?.parent as? FrameLayout)?.removeAllViews()
        val frameLayout = view.findViewById<FrameLayout>(R.id.fl_play)
        frameLayout.addView(mPlayView)
        val mediaSource: MediaSource = mMediaSource.createMediaSource(MediaItem.fromUri(item.play_url))
        //设置ExoPlayer需要播放的多媒体item
        mExoPlayer?.setMediaSource(mediaSource)
        mExoPlayer?.prepare()
        mExoPlayer?.repeatMode = Player.REPEAT_MODE_ONE //无限循环
        mExoPlayer?.addListener(playerListener)
        mExoPlayer?.playWhenReady = true//资源缓冲好后立马播放
    }

    /**
     * 分页提前预加载上拉加载更多
     */
    private fun loadMore() {

    }

    private fun initPlayer() {
        mPlayView = initPlayView()
        mExoPlayer = initExoPlayer()
        // 创建 MediaSource 媒体资源 加载的工厂类
        // 因为由它创建的 MediaSource 能够实现边缓冲边播放的效果,
        // 如果需要播放hls,m3u8 则需要创建DashMediaSource.Factory()
        mMediaSource = ProgressiveMediaSource.Factory(buildCacheDataSource())
        //缓冲完成自动播放
        mExoPlayer?.playWhenReady = true
        mPlayView?.player = mExoPlayer
        mExoPlayer?.prepare()
    }

    /**
     * 创建exoplayer播放器实例
     */
    private fun initPlayView(): StyledPlayerView {
        return StyledPlayerView(this).apply {
            controllerShowTimeoutMs = 10000
            setKeepContentOnPlayerReset(false)
            setShowBuffering(SHOW_BUFFERING_NEVER)//不展示缓冲view
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            useController = false //是否使用默认控制器，如需要可参考PlayerControlView
        }
    }

    private fun initExoPlayer(): ExoPlayer {
        val playerBuilder = ExoPlayer.Builder(this).setMediaSourceFactory(mMediaSource)
        //视频每一帧的画面如何渲染,实现默认的实现类
        val renderersFactory: RenderersFactory = DefaultRenderersFactory(this)
        playerBuilder.setRenderersFactory(renderersFactory)
        //视频的音视频轨道如何加载,使用默认的轨道选择器
        playerBuilder.setTrackSelector(DefaultTrackSelector(this))
        //视频缓存控制逻辑,使用默认的即可
        playerBuilder.setLoadControl(DefaultLoadControl())
        return playerBuilder.build()
    }

    private fun buildCacheDataSource(): DataSource.Factory {
        //创建http视频资源如何加载的工厂对象
        val upstreamFactory = DefaultHttpDataSource.Factory()
        LogUtils.d(LogTag.VIDEO, "cacheDir${this.cacheDir}")
        //创建缓存，指定缓存位置，和缓存策略,为最近最少使用原则,最大为200m
        mCache = SimpleCache(
            application.cacheDir,
            LeastRecentlyUsedCacheEvictor(1024 * 1024 * 200),
            StandaloneDatabaseProvider(this)
        )
        //把缓存对象cache和负责缓存数据读取、写入的工厂类CacheDataSinkFactory 相关联
        val cacheDataSinkFactory =
            CacheDataSink.Factory().setCache(mCache).setFragmentSize(Long.MAX_VALUE)
        return CacheDataSource.Factory()
            .setCache(mCache)
            .setUpstreamDataSourceFactory(upstreamFactory)
            .setCacheReadDataSourceFactory(FileDataSource.Factory())
            .setCacheWriteDataSinkFactory(cacheDataSinkFactory)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }

    private val playerListener = object : Player.Listener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            LogUtils.d(
                LogTag.VIDEO,
                "onPlayerStateChanged playWhenReady == $playWhenReady | playbackState:$playbackState"
            )
            //判断当前视屏是否已经准备好
            val mIsPlaying = mExoPlayer?.bufferedPosition != 0L && playWhenReady
            when (playbackState) {
                Player.STATE_IDLE -> {//播放错误
                }

                Player.STATE_BUFFERING -> {//缓冲中
                }

                Player.STATE_READY -> {//准备完毕，开始播放
                    //播放中
                }

                Player.STATE_ENDED -> {//播放完成，无限循环模式则不会回调这里
                    //播放完成
                }
            }
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            LogUtils.d(
                LogTag.VIDEO, "onIsPlayingChanged isPlaying == $isPlaying "
            )
            if (isPlaying) {
            } else {
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
            LogUtils.d(
                LogTag.VIDEO, "onPlayerError error == $error "
            )
        }
    }
}