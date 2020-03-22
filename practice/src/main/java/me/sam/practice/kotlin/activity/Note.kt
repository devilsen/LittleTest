package me.sam.practice.kotlin.activity

import java.util.*

/**
 * desc :
 * date : 2019-12-03 19:31
 * @author : dongSen
 */
data class Note(
        var id: Int = -1,
        var text: String? = null,
        var isPinned: Boolean = false,
        var createDate: Date = Date(),
        var updateDate: Date? = null
)