package com.example.myyogaapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SplashActivity: Muestra el logo (OM) por un breve tiempo y luego navega a LoginActivity.
 */
public class SplashActivity extends AppCompatActivity {

    // Duración del splash en milisegundos (3 segundos)
    private static final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Usaremos activity_splash.xml

        // Usar un Handler para retrasar y luego iniciar la actividad de Login
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Crear un Intent para iniciar la LoginActivity
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish(); // Cerrar la SplashActivity para que el usuario no pueda volver
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

