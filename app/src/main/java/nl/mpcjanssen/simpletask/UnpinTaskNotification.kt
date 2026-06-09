package nl.mpcjanssen.simpletask

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class UnpinTaskNotification : Service() {
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand(startId=$startId, flags=$flags, action=${intent.action}, extras=${intent.extras?.keySet()})")
        val taskKey = intent.getStringExtra(Constants.EXTRA_PINNED_TASK_KEY)
        if (taskKey == null) {
            Log.e(TAG, "'${Constants.EXTRA_PINNED_TASK_KEY}' not found in intent: $intent")
            return START_STICKY_COMPATIBILITY
        }
        Log.i(TAG, "Unpin action received for taskKey=$taskKey notificationId=${taskKey.hashCode()}")
        TodoApplication.pinnedTaskNotifications.unpinTaskByKey(taskKey)
        stopSelf()
        return START_STICKY_COMPATIBILITY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        private const val TAG = "UnpinTaskNotification"
    }
}
