package com.blueline.harvbest.blueline.Notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.media.app.NotificationCompat;
import android.util.Log;

import com.blueline.harvbest.blueline.MainActivity;
import com.blueline.harvbest.blueline.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class ServicioMensaje extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

//        String Cuerpo = remoteMessage.getNotification().getBody();
  //      Log.e("Cuerpo",Cuerpo);

        if(remoteMessage.getNotification()!= null){
            mostrarNotificacion(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
        }
    }

    private void mostrarNotificacion(String body, String title) {

        Intent i =  new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, PendingIntent.FLAG_ONE_SHOT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        android.support.v4.app.NotificationCompat.Builder  notificavion = new android.support.v4.app.NotificationCompat.Builder(this)
                .setContentTitle(title )
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sonido)
                .setSmallIcon(R.drawable.ca_articulos)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,notificavion.build());
    }

}
