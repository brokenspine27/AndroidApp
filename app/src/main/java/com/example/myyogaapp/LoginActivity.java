package com.example.myyogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * LoginActivity: Maneja el inicio de sesión y la navegación.
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.btn_login);
        TextView registerLink = findViewById(R.id.link_register);
        TextView forgotPasswordLink = findViewById(R.id.link_forgot_password);

        // 1. Manejo del botón de INICIAR SESIÓN (Simulación de flujo)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí iría la lógica real de validación de campos y llamada a API/Firebase.
                // Por ahora, simulamos el éxito y mostramos el AlertDialog.
                showSimulatedActionSuccess(R.string.dialog_login_message, new Runnable() {
                    @Override
                    public void run() {
                        // Después de Aceptar el AlertDialog, navegamos a la pantalla principal
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish(); // Opcional: cierra LoginActivity para que el usuario no pueda volver.
                    }
                });
            }
        });

        // 2. Navegación a Registrar Cuenta
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 3. Navegación a Recuperar Clave
        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
