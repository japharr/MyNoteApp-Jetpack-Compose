package com.japharr.mynoteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.japharr.mynoteapp.R
import com.japharr.mynoteapp.component.NoteButton
import com.japharr.mynoteapp.component.NoteInputText
import com.japharr.mynoteapp.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = { Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon" ) },
            backgroundColor = Color(0xFFDADFE3)
        )
        // Content
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp), text = title,
                label = "Title", onTextChanged = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                })
            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp), text = description,
                label = "Add a note", onTextChanged = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) description = it
                })
            NoteButton(text = "Save",
                onClick = {
                    if(title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(Note(title = title, description = description))
                        title = "";
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_LONG).show()
                    }
                })

            Divider(modifier = Modifier.padding(10.dp))

            LazyColumn() {
                items(notes) { note ->
                    NoteRow(note = note, onNoteClicked = { onRemoveNote(note)})
                }
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    onNoteClicked: (Note) -> Unit,
    note: Note
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .clickable { onNoteClicked(note) },
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")), style = MaterialTheme.typography.caption)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
}