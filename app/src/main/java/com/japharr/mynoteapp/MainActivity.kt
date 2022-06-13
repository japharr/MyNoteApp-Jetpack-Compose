package com.japharr.mynoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.japharr.mynoteapp.screen.NoteScreen
import com.japharr.mynoteapp.screen.NoteViewModel
import com.japharr.mynoteapp.ui.theme.MyNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NoteApp(noteViewModel)
                }
            }
        }
    }
}

@Composable
fun NoteApp(noteViewModel: NoteViewModel) {
    val notes = noteViewModel.noteList.collectAsState().value

    NoteScreen(notes = notes,
        onAddNote = { note -> noteViewModel.addNote(note) },
        onUpdateNote = { note -> noteViewModel.updateNote(note) },
        onRemoveNote = { note -> noteViewModel.removeNote(note) }
    )
}