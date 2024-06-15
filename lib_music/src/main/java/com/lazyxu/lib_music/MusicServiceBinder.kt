package com.lazyxu.lib_music

import android.content.Context
import android.os.Binder

class MusicServiceBinder(private val context: Context) : Binder() {
    var player: Playback? = null
}