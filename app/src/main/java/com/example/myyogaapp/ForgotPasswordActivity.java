package com.example.myyogaapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnRecover;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.et_email_recover); // Asegúrate de que este ID exista en tu layout
        btnRecover = findViewById(R.id.btn_recover);
        TextView backToLoginLink = findViewById(R.id.link_back_to_login);

        mAuth = FirebaseAuth.getInstance();

        btnRecover.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Por favor, ingresa tu correo electrónico", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPasswordActivity.this, "Correo de recuperación enviado con éxito", Toast.LENGTH_LONG).show();
                            finish(); // Volver a la pantalla de login
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "Error: No se pudo enviar el correo. Verifica la dirección.", Toast.LENGTH_LONG).show();
                        }
                    });
        });

        backToLoginLink.setOnClickListener(view -> finish());
    }
}
