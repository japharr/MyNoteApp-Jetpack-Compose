package com.japharr.mynoteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.japharr.mynoteapp.model.Note

class NoteViewModel: ViewModel() {
    private var noteList = mutableStateListOf<Note>()

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes() = noteList
}