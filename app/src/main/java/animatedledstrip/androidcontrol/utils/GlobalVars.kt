package animatedledstrip.androidcontrol.utils

import android.content.SharedPreferences
import animatedledstrip.animationutils.AnimationData
import animatedledstrip.client.AnimationSenderFactory

var ip = "10.0.0.91"
const val IP_KEY = "ip"
var mainSender: AnimationSenderFactory.AnimationSender =
    AnimationSenderFactory.create(ipAddress = ip, port = 6, connectAttemptLimit = 1)

lateinit var mPreferences: SharedPreferences
var connected = false

var animationData = AnimationData()

val onConnectCallback = {
    onConnectCallbacks.forEach { it() }
}

val onConnectCallbacks = mutableListOf<() -> Unit>()

val onDisconnectCallback = {
    onDisconnectCallbacks.forEach { it() }
}

val onDisconnectCallbacks = mutableListOf<() -> Unit>()

