package me.sam.test.jetpack.room


/**
 * desc:
 * date: 2020/05/18 0018
 * @author: dongsen
 */
class UserRepository(private val dao: UserDao) {

    suspend fun loadUsers(ascending: Boolean): List<User> {
        return if (ascending) {
            dao.loadUsersByNameAscending()
        } else {
            dao.loadUserByNameDescending()
        }
    }

    suspend fun getUser(username: String): User = dao.findByName(username)

}