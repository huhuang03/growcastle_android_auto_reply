package com.th.game.growcastle_script.bot_client.manager

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.pm.PermissionInfoCompat
import com.th.game.growcastle_script.bot_client.lg
import java.io.File
import java.io.FileOutputStream
import java.util.jar.Manifest


val pauseLockFile = File(Environment.getExternalStorageDirectory().path + "/growcastle_script", "pause.lock")

class ScriptManager {
    fun isPaused(): Boolean {
        return pauseLockFile.exists()
    }

    private fun checkWriteAndReadPermission(context: Context): Boolean {
        val hasPermission = checkSelfPermission(context, android.Manifest.permission_group.STORAGE) == PackageManager.PERMISSION_GRANTED
        if (hasPermission) {
            return true
        }
        return false
    }

    fun pause() {
        try {
            lg.i("path: ${pauseLockFile.absolutePath}")
//            val createSuccess = pauseLockFile.createNewFile()
            FileOutputStream(pauseLockFile).use {
                it.write(0x0)
            }

//            lg.i("createSuccess: $createSuccess")
        } catch (e: Exception) {
            lg.err(e)
        }
    }

    fun resume() {
        try {
            // 不起作用啊
            pauseLockFile.delete()
        } catch (e: Exception) {
            lg.err(e)
        }
    }

    fun switch(context: Context?) {
        if (!checkWriteAndReadPermission(context!!)) {
            lg.i("permission check failed")
        }

        if (isPaused()) {
            Log.i("th", "switch pause -> resume")
            resume()
        } else {
            Log.i("th", "switch resume -> pause")
            pause()
        }

        context.apply {
            notiMgr.show(this)
        }
    }
}

val scriptMgr = ScriptManager()