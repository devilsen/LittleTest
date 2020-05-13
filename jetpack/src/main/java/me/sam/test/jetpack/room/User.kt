package me.sam.test.jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * desc : https://developer.android.com/training/data-storage/room/defining-data
 * date : 2020/5/7 3:46 PM
 * @author : dongSen
 */
@Entity(tableName = "User")
data class User(
        @PrimaryKey(autoGenerate = true) val uid: Int = 0,
        @ColumnInfo(name = "user_name") val name: String?,
        @ColumnInfo(name = "height") val height: Int
) {
    override fun toString(): String {
        return "uid = $uid name = $name height = $height"
    }
}