package com.blueline.harvbest.blueline.Notificaciones;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseService extends FirebaseInstanceIdService {

@Override
public void onTokenRefresh(){
    super.onTokenRefresh();

    String token = FirebaseInstanceId.getInstance().getToken();

    Log.e("TOKEN",token);
}

}
