package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.NoteManager


class AddNoteActivity : AppCompatActivity() {

    private lateinit var etNoteText: EditText
    private lateinit var btnSaveNote: Button
    private lateinit var fileHandler: FileHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        etNoteText = findViewById(R.id.etNoteText)
        btnSaveNote = findViewById(R.id.btnSaveNote)
        fileHandler = FileHandler(this)

        btnSaveNote.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        if (!PermissionUtil.hasWriteExternalStoragePermission(this)) {
            PermissionUtil.requestWriteExternalStoragePermission(this)
            return
        }

        val noteText = etNoteText.text.toString()
        if (noteText.isNotEmpty()) {
            val note = Note(
                id = System.currentTimeMillis().toString(),
                title = "Note ${System.currentTimeMillis()}",
                content = noteText
            )
            NoteManager.addNote(note)

            fileHandler.saveNoteToMediaStore(noteText)

            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Note is empty", Toast.LENGTH_SHORT).show()
        }
    }
}
