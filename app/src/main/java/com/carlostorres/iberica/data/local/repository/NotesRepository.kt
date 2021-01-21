package com.carlostorres.iberica.data.local.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.carlostorres.iberica.data.local.dao.NotsDao
import com.carlostorres.iberica.data.local.entity.Notes

class NotesRepository( val notesDao: NotsDao ) {

    fun allNotes(): LiveData<List<Notes>> = notesDao.allNotes()

    suspend fun insert(notes: Notes) = notesDao.insertNote(notes)

    suspend fun updateDate(notes: Notes) = notesDao.updateNote(notes)

    suspend fun delete(id: Int) = notesDao.deleteNote(id)
}