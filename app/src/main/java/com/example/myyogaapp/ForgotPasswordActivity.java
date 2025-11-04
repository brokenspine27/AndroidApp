package com.example.myyogaapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * ForgotPasswordActivity: Maneja la solicitud de recuperación de contraseña.
 */
public class ForgotPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button recoverButton = findViewById(R.id.btn_recover);
        TextView backToLoginLink = findViewById(R.id.link_back_to_login);

        // 1. Manejo del botón de ENVIAR (Simulación de flujo)
        recoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí iría la lógica real para enviar el correo de recuperación.
                // Simulamos éxito y mostramos el AlertDialog.
                showSimulatedActionSuccess(R.string.dialog_recover_message, new Runnable() {
                    @Override
                    public void run() {
                        // Después de Aceptar, regresamos a la pantalla de Login
                        finish();
                    }
                });
            }
        });

        // 2. Navegación de vuelta a Login
        backToLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Cierra esta actividad para volver a Login
            }
        });
    }
}
