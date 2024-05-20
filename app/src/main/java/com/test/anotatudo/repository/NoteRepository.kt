package com.test.anotatudo.repository

import com.test.anotatudo.db.NoteDatabase
import com.test.anotatudo.model.Note

class NoteRepository (private var db: NoteDatabase) {

    suspend fun addNote(note: Note) = db.getNoteDao().addNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)

    fun getAllLives() = db.getNoteDao().getAllLives()

    fun searchNote(query: String?)= db.getNoteDao().searchNote(query)

}