package comeon.demo.data.games

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "games")
data class Games(
    @PrimaryKey
    val code: String,
    //val categoryIds: List<Int>,
    val description: String,
    val icon: String,
    val name: String,
    val short_desc: String,
    val theme: String
) : Parcelable