package br.dev.com.notificacao.ui.notificacao

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.core.content.ContextCompat.getSystemService
import br.dev.com.notificacao.ui.notificacao.resultado.AfterNotification
import br.dev.com.notificacao.R

open class Notificacao {

    private lateinit var context: Context
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private val channelId = "1"
    private val description = "Test Notification"

    fun init(mContex: Context) {
        context = mContex
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

     fun solicitarNotificacaoBasica(tituloNotificacao: String, mensagem: String , iconeNotificacao: Int) {
        val intent = Intent(context, AfterNotification::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
            notificationChannel.lightColor = Color.parseColor("#118395")
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(context, channelId)
                .setContentTitle(tituloNotificacao)
                .setContentText(mensagem).setSmallIcon(iconeNotificacao)
                .setLargeIcon(
                    BitmapFactory.decodeResource(context.resources, R.drawable
                    .ic_launcher_background)).setContentIntent(pendingIntent)

        }
        notificationManager.notify(12345, builder.build())
    }

    fun solicitarNotificacaoTextoLongo(tituloNotificacao: String, mensagem: String , iconeNotificacao: Int) {

        val intent = Intent(context, AfterNotification::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        /*Observe que o construtor NotificationCompat.Builder exige que você forneça um ID do canal.
        Isso é necessário para que exista compatibilidade com o Android 8.0 (API de nível 26) e versões mais recentes,
        mas é ignorado pelas versões anteriores.*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.lightColor = Color.parseColor("#118395")
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(context, channelId)
                .setContentTitle(tituloNotificacao)
                .setContentText(mensagem)
                .setSmallIcon(iconeNotificacao)
                .setStyle(Notification.BigTextStyle()
                     .bigText("Much longer text that cannot fit one line..."))
                .setLargeIcon(
                    BitmapFactory.decodeResource(context.resources, R.drawable
                        .ic_launcher_background)).setContentIntent(pendingIntent)

        }
        notificationManager.notify(12345, builder.build())
    }

    fun solicitarNotificacaoPendente(tituloNotificacao: String, mensagem: String , iconeNotificacao: Int) {

        val intent = Intent(context, AfterNotification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        builder = Notification.Builder(context, channelId)
            .setSmallIcon(iconeNotificacao)
            .setContentTitle(tituloNotificacao)
            .setContentText(mensagem)
            .setPriority(Notification.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(12345, builder.build())
    }

}