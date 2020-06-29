package animatedledstrip.androidcontrol.utils

import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate


// Keys for saving settings
const val DARK_KEY = "dark_mode"
const val IP_KEY = "ip_addrs"
const val PORT_KEY = "port_sel"
const val NOTIFICATION_KEY = "do_notification"

// Loaded preferences
lateinit var mPreferences: SharedPreferences

// Track whether the notification should be shown
var showNotification = true


fun loadPreferences() {
    if (mPreferences.getString(DARK_KEY, null) == null) saveDefaultPreferences()

    setNightModeFromPreferences()
    setIPsFromPreferences()
    setPortFromPreferences()
    setShowNotificationFromPreferences()
}

/**
 * Set dark mode setting
 */
fun setNightModeFromPreferences() {
    AppCompatDelegate.setDefaultNightMode(
        when (mPreferences.getString(DARK_KEY, null)) {
            "Light" -> AppCompatDelegate.MODE_NIGHT_NO
            "Dark" -> AppCompatDelegate.MODE_NIGHT_YES
            else ->
                if (Build.VERSION.SDK_INT >= 29) AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                else AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        }
    )
}

fun setPortFromPreferences() {
    mainSender.port = mPreferences.getInt(PORT_KEY, defaultPort)
}

fun setShowNotificationFromPreferences() {
    showNotification = mPreferences.getBoolean(NOTIFICATION_KEY, true)
}

fun setIPsFromPreferences() {
    IPs.clear()
    val ipList = mPreferences.getStringSet(IP_KEY, null)?.toString() ?: ""

    for (ip in ipList.split(",")) {
        val ipFormatted =
            ip.removePrefix("[")
                .removeSuffix("]")
                .removePrefix(" ")

        if (ip != "") IPs.add(ipFormatted)
    }
}

fun saveDefaultPreferences() {
    mPreferences.edit()
        .putString(DARK_KEY, "Default")
        .putInt(PORT_KEY, defaultPort)
        .putBoolean(NOTIFICATION_KEY, true)
        .apply()

}