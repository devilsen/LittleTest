package me.sam.test.jetpack.room

import androidx.room.*

/**
 * desc : Data Access Objects
 * date : 2020/5/7 4:01 PM
 * @author : dongSen
 */
@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM USER")
    fun clear()

    @Query("SELECT * FROM USER")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE UID IN (:userIds)")
    fun loadById(userIds: IntArray): List<User>

    @Query("SELECT *FROM USER WHERE user_name LIKE :name LIMIT 1")
    fun findByName(name: String): User

    // 协程test
    @Query("SELECT * FROM USER ORDER BY uid ASC")
    suspend fun loadUsersByNameAscending(): List<User>

    @Query("SELECT *FROM USER ORDER BY uid DESC")
    suspend fun loadUserByNameDescending(): List<User>
}