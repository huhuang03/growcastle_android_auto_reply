package com.th.game.growcastle_script.bot_client.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.th.game.growcastle_script.bot_client.R
import com.th.game.growcastle_script.bot_client.boradcast.OpBroadCastReceiver
import com.th.game.growcastle_script.bot_client.boradcast.REQ_OP

const val CHANNEL_NAME = "whatis_channel_name"
const val CHANNEL_ID = "whatis_channel_id"
const val NOTIFICATION_ID = 100

class NotiManager {
    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = "This is the channel desc?"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun show(context: Context) {
        createNotificationChannel(context)

        val opIntent = Intent(context, OpBroadCastReceiver::class.java).apply {
        }
        val opPendingIntent = PendingIntent.getBroadcast(context, REQ_OP, opIntent, 0)

        val builder = NotificationCompat.Builder(context.applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("运行状态")
            .setContentText(if(scriptMgr.isPaused()) "已暂停" else "运行中")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_launcher_background, if (scriptMgr.isPaused()) "开始" else "暂停", opPendingIntent)
            .setAutoCancel(false)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}

val notiMgr = NotiManager()