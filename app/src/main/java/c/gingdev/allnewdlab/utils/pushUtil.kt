package c.gingdev.allnewdlab.utils

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import c.gingdev.allnewdlab.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class pushUtil: FirebaseMessagingService() {
    private val TAG = "FCM"

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.e("TOKEN", token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        remoteMessage?.data?.isNotEmpty()?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            notifyData(remoteMessage)
        }
    }

    private fun notifyData(remoteMessage: RemoteMessage) {
        var title: String?
        var message: String?
        remoteMessage.data.let {
            title = it.get("title")
            message = it.get("message")
        }
        
        Build.VERSION.SDK_INT.upperOreo {
            notifyOverOreo(title, message)
        } underOreo {
            notifyUnderOreo(title, message)
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun notifyOverOreo(title: String?, message: String?) {

        val Channel_Id = getString(R.string.notify_channel_id)
        val Channel_Name = getString(R.string.notify_channel_name)

        val Channel = NotificationChannel(
            Channel_Id,
            Channel_Name,
            NotificationManager.IMPORTANCE_DEFAULT)
            .apply {
                enableVibration(true)
                enableLights(true)
                setShowBadge(false)
                vibrationPattern = longArrayOf(200, 50, 50) }

        val manager = (getSystemService(Context.NOTIFICATION_SERVICE)) as NotificationManager
        manager.createNotificationChannel(Channel)

        val notifyBuilder = NotificationCompat.Builder(this, Channel_Id)
            .apply {
                setSmallIcon(R.drawable.ic_rice)
                setContentTitle(title)
                setContentText(message)
                setChannelId(Channel_Id)
                setAutoCancel(true)
                setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE) }

        manager.notify(1024, notifyBuilder.build())
    }

    private fun notifyUnderOreo(title: String?, message: String?) {
        val manager = (getSystemService(Context.NOTIFICATION_SERVICE)) as NotificationManager

        val notifyBuilder = NotificationCompat.Builder(this, "")
            .apply {
                setSmallIcon(R.drawable.ic_rice)
                setContentTitle(title)
                setContentText(message)
                setAutoCancel(true)
                setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE) }

        manager.notify(1024, notifyBuilder.build())
    }
}

private infix fun Int.underOreo(f: () -> Unit) {
    if (this < Build.VERSION_CODES.O) f()
}
private fun Int.upperOreo(f: () -> Unit): Int {
    return if (this >= Build.VERSION_CODES.O) { f(); this }
    else this
}
