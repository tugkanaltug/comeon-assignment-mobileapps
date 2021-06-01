package comeon.demo.util

import android.os.Handler
import android.os.Looper

private val mainHandler = Handler(Looper.getMainLooper())

/**
 * Main thread handler.
 *
 * @return main thread handler.
 */
fun getMainHandler(): Handler {
    return mainHandler
}