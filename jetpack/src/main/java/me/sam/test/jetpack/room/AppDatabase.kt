package me.sam.test.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import me.sam.test.jetpack.util.SingletonHolder

/**
 * desc :
 * date : 2020/5/7 4:21 PM
 * @author : dongSen
 */
@Database(entities = [User::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(
                it.applicationContext,
                AppDatabase::class.java, "database_jetpack")
//                 .allowMainThreadQueries()
                .addMigrations(Migration_2_3())
                .build()
    })

    abstract fun userDao(): UserDao

    // 添加列
    class Migration_1_2 : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE User ADD COLUMN weight INTEGER NOT NULL DEFAULT 1")
        }
    }

    // 删除列
    class Migration_2_3 : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE user_temp (uid INTEGER PRIMARY KEY NOT NULL ,user_name TEXT,height INTEGER NOT NULL DEFAULT 1)")
            database.execSQL("INSERT INTO user_temp (uid,user_name,height) SELECT uid,user_name,height FROM User")
            database.execSQL("DROP TABLE User")
            database.execSQL("ALTER TABLE user_temp RENAME to User")
        }
    }
}