package nl.mpcjanssen.simpletask

import android.content.Intent
import android.util.Log
import android.app.Service
import android.os.IBinder
import kotlin.concurrent.thread

class MarkTaskDone : Service() {
    val TAG = "MarkTaskDone"

    public override fun onStartCommand (intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand(startId=$startId, flags=$flags, action=${intent.action}, extras=${intent.extras?.keySet()})")
        val taskKey = intent.getStringExtra(Constants.EXTRA_PINNED_TASK_KEY)
        if (taskKey == null) {
            Log.e(TAG, "'${Constants.EXTRA_PINNED_TASK_KEY}' not found in intent: $intent")
            return START_STICKY_COMPATIBILITY
        }
        Log.i(TAG, "Complete action received for taskKey=$taskKey notificationId=${taskKey.hashCode()}")
        thread(start = true) {
            Log.i(TAG, "Complete worker started for taskKey=$taskKey")
            val completed = TodoApplication.pinnedTaskNotifications.completeTaskFromNotification(taskKey)
            Log.i(TAG, "Complete worker finished for taskKey=$taskKey completed=$completed")
            if (!completed) {
                Log.e(TAG, "task with key '$taskKey' not found in todo list")
            }
            stopSelfResult(startId)
        }
        return START_STICKY_COMPATIBILITY
    }

    public override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
