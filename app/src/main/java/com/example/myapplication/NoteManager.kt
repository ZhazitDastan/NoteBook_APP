package com.example.myapplication

class NoteManager {
    companion object {
        private val notes: MutableList<Note> = mutableListOf()

        fun addNote(note: Note) {
            notes.add(note)
        }

        fun searchNotes(query: String): List<Note> {
            return notes.filter {
                it.title.contains(
                    query,
                    ignoreCase = true
                ) || it.content.contains(query, ignoreCase = true)
            }
        }

        fun getNoteContent(noteId: String): String? {
            val note = notes.find { it.id == noteId }
            return note?.content
        }

        fun getNoteById(id: String): Note? {
            return notes.find { it.id == id }
        }

    }
}




