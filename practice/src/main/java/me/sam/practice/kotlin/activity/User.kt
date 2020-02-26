package me.sam.practice.kotlin.activity

import java.lang.IllegalArgumentException

/**
 * desc : 局部函数
 * date : 2019-12-04 14:58
 * @author : dongSen
 */
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id} : empty $fieldName")
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
}