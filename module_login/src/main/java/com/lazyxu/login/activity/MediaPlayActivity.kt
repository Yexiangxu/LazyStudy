package com.lazyxu.login.activity

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.widget.SeekBar
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.gyf.immersionbar.ImmersionBar
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.StringUtils
import com.lazyxu.base.utils.TimerTaskManager
import com.lazyxu.login.R
import com.lazyxu.login.databinding.ActivityPlayBinding
import com.lazyxu.login.entity.MusicEntity


//@Route(path = ARouterPath.Function.MEDIAPLAYER)
class MediaPlayActivity : BaseVbActivity<ActivityPlayBinding>() {
    @JvmField
    @Autowired
    var playpos: Int = 0

    @JvmField
    @Autowired
    var playlist: List<MusicEntity>? = null


    private var timerTaskManager: TimerTaskManager? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun initStatusbar(color: Int) {
        ImmersionBar.with(this)
            .autoDarkModeEnable(true)
            .statusBarDarkFont(true, 0.2f)
            .navigationBarColor(com.lazyxu.base.R.color.black)
            .navigationBarDarkIcon(true)
            .keyboardEnable(false)
            .init()
    }

    override fun initView() {
        timerTaskManager = TimerTaskManager()
        timerTaskManager?.setUpdateProgressTask {
            mediaPlayer?.let {
                mViewBinding.tvCurrentTime.text = StringUtils.calculateTime(it.currentPosition)
                mViewBinding.tvAllTime.text = StringUtils.calculateTime(it.duration)
                mViewBinding.sbMusic.max = it.duration
                mViewBinding.sbMusic.progress = it.currentPosition
                LogUtils.d("currentPosition=${it.currentPosition},duration=${it.duration}")
            }
        }
        timerTaskManager?.startToUpdateProgress()
        playSong()
    }


    override fun initClicks() {
        //进度SeekBar
        mViewBinding.sbMusic.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                mediaPlayer?.seekTo(seekBar.progress)
                mViewBinding.tvCurrentTime.text = StringUtils.calculateTime(seekBar.progress)
            }
        })
        mViewBinding.tbTitle.setNavigationOnClickListener {
            finish()
        }
        mViewBinding.ivPlay.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                isPlay = false
                mediaPlayer?.pause()
                mViewBinding.ivPlay.setImageResource(R.drawable.svg_music_play)
            } else {
                isPlay = true
                mediaPlayer?.start()
                mViewBinding.ivPlay.setImageResource(R.drawable.svg_music_pause)
            }
        }
        mViewBinding.ivLast.setOnClickListener {
            if (playpos > 0) {
                playpos -= 1
                playSong()
            }

        }
        mViewBinding.ivNext.setOnClickListener {
            playlist?.let { list ->
                if (playpos < list.size) {
                    playpos += 1
                    playSong()
                }
            }
        }
    }


    private fun playSong() {
        playlist?.let { list ->
            mViewBinding.tvTitle.text = list[playpos].name
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer()
                mediaPlayer?.setOnCompletionListener {
                    if (playpos < list.size) {
                        playpos += 1
                        playSong()
                    }
                }
                mediaPlayer?.setOnErrorListener { mp, what, extra ->
                    isPlay = false
                    mViewBinding.ivPlay.setImageResource(R.drawable.svg_music_play)
                    true
                }
            }
            mViewBinding.ivPlay.setImageResource(R.drawable.svg_music_pause)
            mediaPlayer?.let {
                try {
                    it.reset()
                    it.setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
                    )
                    it.setDataSource(list[playpos].media_url)
                    it.prepare()
                    it.start()
                } catch (_: Exception) {
                }
            }
        }
    }


    private var isPlay = true

    override fun onPause() {
        super.onPause()
        if (isPlay) {
            mediaPlayer?.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (isPlay) {
            mediaPlayer?.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        timerTaskManager?.stopToUpdateProgress()
        timerTaskManager = null
    }
}