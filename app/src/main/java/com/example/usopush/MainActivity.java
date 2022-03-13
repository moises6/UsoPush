package com.example.usopush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {
TextView tvtoken;
Button bntobtener;
    private static final String ONESIGNAL_APP_ID = "ff6f86db-e2d8-4b99-9da1-e941a4a44c5f";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        tvtoken = findViewById(R.id.tvToken);
        bntobtener = findViewById(R.id.bntObtener);

        bntobtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerToken();
            }
        });

    }

    public void obtenerToken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(
                new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "No se pudo obtenre el token", Toast.LENGTH_SHORT).show();
                        }else{
                            tvtoken.setText("Token"+task.getResult());

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "error interno"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}