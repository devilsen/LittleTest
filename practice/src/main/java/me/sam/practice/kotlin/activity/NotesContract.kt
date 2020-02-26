package me.sam.practice.kotlin.activity

import me.sam.practice.kotlin.activity.NotesContract.NoteTable.TEXT
import me.sam.practice.kotlin.activity.NotesContract.NoteTable._ID
import me.sam.practice.kotlin.activity.NotesContract.NoteTable._TABLE_NAME

/**
 * desc :
 * date : 2019-12-03 19:51
 * @author : dongSen
 */
object NotesContract {

    object NoteTable {
        const val _ID = "id"
        const val TEXT = "text"
        const val _TABLE_NAME = "notes"
    }

    val SQL_CREATE_ENTERS = """CREATE TABLE $_TABLE_NAME(
        $_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        $TEXT TEXT)""".trimMargin()

}