package me.sam.test.jetpack.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.sam.test.jetpack.room.User
import me.sam.test.jetpack.room.UserRepository

/**
 * desc:
 * date: 2020/05/14 0014
 * @author: dongsen
 */
class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    fun onSortAscending() = getUsers(ascending = true)
    fun onSortDescending() = getUsers(ascending = false)

    private fun getUsers(ascending: Boolean) {
        viewModelScope.launch {
            _users.value = repository.loadUsers(ascending)
        }
    }

}