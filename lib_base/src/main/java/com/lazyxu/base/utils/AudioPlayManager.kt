package com.lazyxu.base.utils

import android.content.Context
import android.media.SoundPool
import com.lazyxu.base.R
import com.lazyxu.base.constants.SpKey

class AudioPlayManager private constructor() {
    private var mContext: Context? = null
    private var mSoundPool: SoundPool? = null
    private var clickSoundId = 0

    /**
     * 初始化
     */
    fun init(context: Context?) {
        if (context == null) {
            return
        }
        mContext = context
        mSoundPool = SoundPool.Builder().setMaxStreams(1).build()
        mSoundPool?.let {
            clickSoundId = it.load(mContext, R.raw.click, 1)
        }
    }

    /**
     * 播放点击音效
     */
    fun clickSound() {
        if (!SpUtils.getBoolean(SpKey.OPEN_CLICK_SOUND)) {
            return
        }
        playClickSound(clickSoundId)
    }

    private fun playClickSound(soundId: Int) {
        if (soundId == 0) {
            return
        }
        try {
            mSoundPool?.play(soundId, 1f, 1f, 0, 0, 1f)
        } catch (t: Throwable) {
        }
    }

    companion object {
        val instance: AudioPlayManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AudioPlayManager()
        }
    }
}