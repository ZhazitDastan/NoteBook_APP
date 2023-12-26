package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import java.io.IOException

class FileHandler(private val context: Context) {

    @Throws(IOException::class)
    fun saveNoteToMediaStore(noteText: String) {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "note.txt")
            put(MediaStore.Images.Media.MIME_TYPE, "text/plain")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS)
        }

        val externalUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val resolver = context.contentResolver
        val insertUri = resolver.insert(externalUri, values)

        if (insertUri != null) {
            resolver.openOutputStream(insertUri).use { outputStream ->
                outputStream?.bufferedWriter().use { it?.write(noteText) }
            }
        }
    }
}
