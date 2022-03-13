package com.example.usopush;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;

public class FCMservice extends FirebaseMessagingService {

public void onNewToke (@NonNull String s){
    super.onNewToken(s);
    int token = Log.i("TOKEN", "token id" + s);
}

}
