package jalanechrissia.rivera.com.spotify_midermoutput

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Jalane Chrissia on 24/01/2018.
 */
data class SongModel(
        val song_title: String = " ",
        val song_artist: String = " ",
        val song_album: String = " ",
        val song_path: String = " ",
        var stat: Int = 0
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()
    ){
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(song_title)
        parcel.writeString(song_artist)
        parcel.writeString(song_album)
        parcel.writeString(song_path)
        parcel.writeInt(stat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SongModel> {
        override fun createFromParcel(parcel: Parcel): SongModel {
            return SongModel(parcel)
        }

        override fun newArray(size: Int): Array<SongModel?> {
            return arrayOfNulls(size)
        }
    }

}