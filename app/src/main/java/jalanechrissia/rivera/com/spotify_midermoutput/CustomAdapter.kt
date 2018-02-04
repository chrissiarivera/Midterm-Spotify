package jalanechrissia.rivera.com.spotify_midermoutput

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Jalane Chrissia on 24/01/2018.
 */
class CustomAdapter(val songModel: ArrayList<SongModel>,val context: Context, val mainAct: MainActivity): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    var currentSong:Int = 0
    var view: View? = null
    var mContext = context
    val allMusicList: ArrayList<String> = ArrayList()
    companion object {
        val MUSICLIST = "list"
        val MUSICPOS = "pos"
        val MUSICPLAY = "play"
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mLinear = itemView.findViewById<LinearLayout>(R.id.linearLayout) as LinearLayout
        val txtSongTitle = itemView.findViewById<TextView>(R.id.txtTitle) as TextView
        val txtSongArtist = itemView.findViewById<TextView>(R.id.txtSinger) as TextView
        val txtSongAlbum = itemView.findViewById<TextView>(R.id.txtAlbum) as TextView
    }

    override fun getItemCount(): Int {
        return songModel.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.song_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val song: SongModel = songModel[position]
        holder?.txtSongTitle?.text = song.song_title
        holder?.txtSongArtist?.text = song.song_artist
        holder?.txtSongAlbum?.text = song.song_album

        holder?.mLinear?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                currentSong = position
                holder?.txtSongTitle?.setTextColor(Color.parseColor("#1DB954"))
                for (i in 0 until songModel.size) {
                    allMusicList.add(songModel[i].song_path)
                }

                try {
                    val fragment = SongFragment.newInstance(song.song_title, song.song_artist)
                    mainAct.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_play, fragment)
                            .commit()
                } catch (e: Exception) {
                    Toast.makeText(mainAct, "Error!", Toast.LENGTH_SHORT)
                }

                var songIntent = Intent(mContext, PlayMusicService::class.java)
                songIntent.putStringArrayListExtra(MUSICLIST, allMusicList)
                songIntent.setAction(MUSICPLAY)
                songIntent.putExtra(MUSICPOS, position)
                mContext.startService(songIntent)
            }
        })
    }
}