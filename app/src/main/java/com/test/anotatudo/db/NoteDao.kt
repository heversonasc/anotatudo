package com.test.anotatudo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.anotatudo.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY id")
    fun getAllLives(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE nateTitle LIKE:query OR nobody LIKE :query")
    fun searchNote(query: String?): LiveData<List<Note>>
}