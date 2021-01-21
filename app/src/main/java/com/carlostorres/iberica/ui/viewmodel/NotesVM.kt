package com.carlostorres.iberica.ui.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.carlostorres.iberica.data.local.NotesRoomDatabase
import com.carlostorres.iberica.data.local.entity.Notes
import com.carlostorres.iberica.data.local.repository.NotesRepository
import com.carlostorres.iberica.model.intrfacs.HomeListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesVM(application: Application): AndroidViewModel(application) {

    // Database
    private val notesDao = NotesRoomDatabase.getDatabase(application).notesDado()
    private val repository: NotesRepository
    val allNotes: LiveData<List<Notes>>

    init {
        repository = NotesRepository(notesDao)
        allNotes = repository.allNotes()
    }

    fun insert(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(notes)
        }
    }

    fun update(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDate(notes)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }

}