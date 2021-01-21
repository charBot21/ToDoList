package com.carlostorres.iberica.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.carlostorres.iberica.data.local.entity.Notes

@Dao
interface NotsDao {
    @Query( "SELECT * from notes_table ORDER BY timeStamp DESC")
    fun allNotes(): LiveData<List<Notes>>

    @Insert
    fun insertNote(notes: Notes)

    @Update
    fun updateNote(notes: Notes)

    @Query("DELETE FROM notes_table WHERE id = :id")
    fun deleteNote(id: Int)
}