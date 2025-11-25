package com.example.myyogaapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddPoseActivity extends AppCompatActivity {

    private EditText etPoseTitle, etPoseDescription, etImageUrl;
    private Button btnSavePose;
    private FirebaseFirestore db;

    private boolean isEditing = false;
    private String poseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pose);

        db = FirebaseFirestore.getInstance();

        etPoseTitle = findViewById(R.id.et_pose_title);
        etPoseDescription = findViewById(R.id.et_pose_description);
        etImageUrl = findViewById(R.id.et_image_url);
        btnSavePose = findViewById(R.id.btn_save_pose);

        // Comprobar si estamos en modo de edición
        if (getIntent().hasExtra("isEditing")) {
            isEditing = getIntent().getBooleanExtra("isEditing", false);
            poseId = getIntent().getStringExtra("poseId");

            if (isEditing) {
                setTitle("Editar Postura");
                etPoseTitle.setText(getIntent().getStringExtra("title"));
                etPoseDescription.setText(getIntent().getStringExtra("description"));
                etImageUrl.setText(getIntent().getStringExtra("imageUrl"));
            }
        }

        btnSavePose.setOnClickListener(v -> savePose());
    }

    private void savePose() {
        String title = etPoseTitle.getText().toString().trim();
        String description = etPoseDescription.getText().toString().trim();
        String imageUrl = etImageUrl.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(imageUrl)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Pose pose = new Pose(title, description, imageUrl);

        if (isEditing) {
            // Actualizar una postura existente
            db.collection("poses").document(poseId).set(pose)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Postura actualizada con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error al actualizar la postura", Toast.LENGTH_SHORT).show());
        } else {
            // Crear una nueva postura
            db.collection("poses").add(pose)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Postura guardada con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error al guardar la postura", Toast.LENGTH_SHORT).show());
        }
    }
}
