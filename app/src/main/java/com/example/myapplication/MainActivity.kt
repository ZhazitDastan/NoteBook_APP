package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

        private lateinit var editTextNote: EditText
        private lateinit var buttonSave: Button
        private lateinit var gridLayout: GridLayout
        private lateinit var btnAddNote: ImageButton
        private lateinit var btnNotes: ImageButton
        private lateinit var btnTasks: ImageButton
        private lateinit var btnSettings: ImageButton
        private var noteCount = 0
        private lateinit var noteManager: NoteManager

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                editTextNote = findViewById(R.id.etSearch)
                buttonSave = findViewById(R.id.btnSaveNote)
                gridLayout = findViewById(R.id.settingsGrid)
                btnAddNote = findViewById(R.id.btnAddNote)
                btnNotes = findViewById(R.id.btnNotes)
                btnTasks = findViewById(R.id.btnTasks)
                btnSettings = findViewById(R.id.btnSettings)
                noteManager = NoteManager()

                buttonSave.setOnClickListener {
                        saveNote()
                }

                btnAddNote.setOnClickListener {
                        addNote()
                }

                btnNotes.setOnClickListener {
                        openNotesTab()
                }

                btnTasks.setOnClickListener {
                        openTasksTab()
                }

                btnSettings.setOnClickListener {
                        openSettings()
                }
        }

        private fun saveNote() {
                val noteText = editTextNote.text.toString()
                if (noteText.isNotEmpty()) {
                        val note = Note(
                                id = System.currentTimeMillis().toString(),
                                title = "Note $noteCount",
                                content = noteText
                        )
                        NoteManager.addNote(note)

                        val inflater = LayoutInflater.from(this)
                        val newNoteView = inflater.inflate(R.layout.note_square, null)
                        val noteContentTextView: TextView = newNoteView.findViewById(R.id.noteContent)
                        noteContentTextView.text = noteText

                        val params = GridLayout.LayoutParams()
                        params.width = GridLayout.LayoutParams.WRAP_CONTENT
                        params.height = GridLayout.LayoutParams.WRAP_CONTENT
                        params.rowSpec = GridLayout.spec(noteCount / 2, GridLayout.CENTER)
                        params.columnSpec = GridLayout.spec(noteCount % 2, GridLayout.CENTER)

                        newNoteView.layoutParams = params
                        gridLayout.addView(newNoteView)

                        noteCount++
                        editTextNote.text.clear()
                }
        }

        private fun addNote() {
                val intent = Intent(this, AddNoteActivity::class.java)
                startActivity(intent)
        }

        private fun openNotesTab() {

        }

        private fun openTasksTab() {

        }

        private fun openSettings() {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
        }
}