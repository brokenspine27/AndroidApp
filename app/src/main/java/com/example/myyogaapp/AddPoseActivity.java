package com.example.myyogaapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddPoseActivity extends AppCompatActivity {

    private EditText etPoseTitle, etPoseDescription;
    private Button btnSavePose;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pose);

        db = FirebaseFirestore.getInstance();

        etPoseTitle = findViewById(R.id.et_pose_title);
        etPoseDescription = findViewById(R.id.et_pose_description);
        btnSavePose = findViewById(R.id.btn_save_pose);

        // TODO: Implementar la selección de imagen (paso complejo)
        // Por ahora, se guardará sin imagen o con un nombre de imagen predefinido.

        btnSavePose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewPose();
            }
        });
    }

    private void saveNewPose() {
        String title = etPoseTitle.getText().toString().trim();
        String description = etPoseDescription.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Por ahora, no se asigna ninguna imagen. El usuario podría introducir un nombre de imagen.
        String imageName = "ic_default_pose"; // Un placeholder

        Pose newPose = new Pose(title, description, imageName);

        db.collection("poses").add(newPose)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddPoseActivity.this, "Postura guardada con éxito", Toast.LENGTH_SHORT).show();
                        finish(); // Cierra la actividad y vuelve a HomeActivity
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddPoseActivity.this, "Error al guardar la postura: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
