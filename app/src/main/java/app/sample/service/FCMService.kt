package app.sample.service

class FCMService /*: FirebaseMessagingService()*/ {

    /*private var _receiver: BroadcastReceiver? = null
    private lateinit var _filter: IntentFilter

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onNewToken(token: String) {
        Log.d(
            "TAG",
            "Refreshed token: $token"
        )
    }

    companion object {
        const val CHANNEL_ID = "KawawaWalletServiceChannel"
    }


    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage != null) {

            sharedPref = SharedPref(applicationContext)
            Log.e("remoteMessage123", "--------" + remoteMessage.data)
            //Log.e("remoteMessageNotification", "--------" + remoteMessage.notification?.body)
            Log.e("type", "--------" + remoteMessage.data.get("result_data"))
            Log.e(
                "Login",
                "---Status----" + sharedPref.getData(SharedPref.IsLogin, false) as Boolean
            )
            var notificationObj = JSONObject(remoteMessage.data.get("result_data"))
            Log.e("Noti", "Object > $notificationObj")
            Log.e("type", "type > " + notificationObj.optString("type"))

            if (remoteMessage.data.isNotEmpty()) {
                when (notificationObj.optString("type")) {

                    Constant.Wallet -> {
                        val intent = Intent(applicationContext, SplashActivity::class.java)

                        intent.putExtra("Flag", Constant.Wallet)
                        intent.flags = (FLAG_ACTIVITY_NEW_TASK
                                or FLAG_ACTIVITY_CLEAR_TASK)
                        sendNotification(
                            notificationObj.optString("title").toString(),
                            notificationObj.optString("body").toString(),
                            intent
                        )
                    }

                    Constant.Notification_List -> {
                        val intent = Intent(applicationContext, SplashActivity::class.java)

                        intent.putExtra("Flag", Constant.Notification_List)
                        intent.flags = (FLAG_ACTIVITY_NEW_TASK
                                or FLAG_ACTIVITY_CLEAR_TASK)
                        sendNotification(
                            notificationObj.optString("title").toString(),
                            notificationObj.optString("body").toString(),
                            intent
                        )
                    }

                    Constant.OTP -> {
                        val intent = Intent(applicationContext, SplashActivity::class.java)

                        intent.putExtra("Flag", Constant.OTP)
                        intent.flags = (FLAG_ACTIVITY_NEW_TASK
                                or FLAG_ACTIVITY_CLEAR_TASK)
                        sendNotification(
                            notificationObj.optString("title").toString(),
                            notificationObj.optString("body").toString(),
                            intent
                        )
                    }

                    Constant.Transaction_History -> {
                        val intent = Intent(applicationContext, SplashActivity::class.java)

                        intent.putExtra("Flag", Constant.Transaction_History)
                        intent.flags = (FLAG_ACTIVITY_NEW_TASK
                                or FLAG_ACTIVITY_CLEAR_TASK)
                        sendNotification(
                            notificationObj.optString("title").toString(),
                            notificationObj.optString("body").toString(),
                            intent
                        )
                    }

                    Constant.Transaction_REQUEST -> {
                        val intent = Intent(applicationContext, SplashActivity::class.java)

                        intent.putExtra("Flag", Constant.Transaction_REQUEST)
                        intent.flags = (FLAG_ACTIVITY_NEW_TASK
                                or FLAG_ACTIVITY_CLEAR_TASK)
                        sendNotification(
                            notificationObj.optString("title").toString(),
                            notificationObj.optString("body").toString(),
                            intent
                        )
                    }

                    Constant.BUSINESS_PROFILE -> {
                        val intent = Intent(applicationContext, SplashActivity::class.java)

                        intent.putExtra("Flag", Constant.Transaction_REQUEST)
                        intent.flags = (FLAG_ACTIVITY_NEW_TASK
                                or FLAG_ACTIVITY_CLEAR_TASK)
                        sendNotification(
                            notificationObj.optString("title").toString(),
                            notificationObj.optString("body").toString(),
                            intent
                        )
                    }

                }
            }
        }
    }

    private fun sendNotification(title: String, message: String, intent: Intent) {

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        var pendingIntent: PendingIntent? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            pendingIntent = PendingIntent.getActivity(
                this,
                0 *//* Request code *//*,
                intent,
                PendingIntent.FLAG_MUTABLE
            )
        } else {

            pendingIntent = PendingIntent.getActivity(
                this,
                0 *//* Request code *//*,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri =
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(getNotificationIcon())
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(message)
                )
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build())
    }

    private fun getNotificationIcon(): Int {
        val useWhiteIcon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        return if (useWhiteIcon) R.drawable.ic_icon else R.drawable.ic_icon
    }
*/
}