package jalanechrissia.rivera.com.spotify_midermoutput

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

/**
 * Created by Jalane Chrissia on 28/01/2018.
 */
class PlayMusicService: Service() {

    private val PLAY = "play"
    private val PAUSE = "pause-song"
    private val RESUME = "resume-song"
    var currentPos: Int = 0
    var musicDataList:ArrayList<String> = ArrayList()
    var mp: MediaPlayer? = null
    var position = 0

    override fun onBind(p0: Intent?): IBinder ? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent!!.getAction().equals(PLAY)) {
            currentPos = intent!!.getIntExtra(CustomAdapter.MUSICPOS, 0)
            musicDataList = intent.getStringArrayListExtra(CustomAdapter.MUSICLIST)

            if (mp != null) {
                mp!!.stop()
                mp!!.release()
                mp = null
            }

            mp = MediaPlayer()
            mp!!.setDataSource(musicDataList[currentPos])
            mp!!.prepare()
            mp!!.start()
        }

        if (intent!!.getAction().equals(PAUSE)) {
            position = mp!!.getCurrentPosition()
            mp!!.pause()
        }

        if (intent!!.getAction().equals(RESUME)){
            mp!!.seekTo (position)
            mp!!.start()
        }

        return super.onStartCommand(intent, flags, startId)
    }
}