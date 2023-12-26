package com.example.myapplication

// PermissionUtil.kt
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtil {

    const val REQUEST_WRITE_EXTERNAL_STORAGE = 1

    fun hasWriteExternalStoragePermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestWriteExternalStoragePermission(activity: AppCompatActivity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_WRITE_EXTERNAL_STORAGE
        )
    }
}
