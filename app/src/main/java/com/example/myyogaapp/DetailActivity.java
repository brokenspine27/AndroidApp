package com.example.myyogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailActivity extends AppCompatActivity {

    private String poseId, title, description, imageUrl;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = FirebaseFirestore.getInstance();

        ImageView ivPose = findViewById(R.id.iv_pose_detail);
        TextView tvTitle = findViewById(R.id.tv_title_detail);
        TextView tvDescription = findViewById(R.id.tv_description_detail);
        Button btnBack = findViewById(R.id.btn_back);
        Button btnEdit = findViewById(R.id.btn_edit);
        Button btnDelete = findViewById(R.id.btn_delete);

        // Recuperar los datos del Intent
        poseId = getIntent().getStringExtra("poseId");
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        imageUrl = getIntent().getStringExtra("imageUrl");

        // Rellenar la vista con los datos
        tvTitle.setText(title);
        tvDescription.setText(description);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this).load(imageUrl).into(ivPose);
        }

        // Configurar los listeners de los botones
        btnBack.setOnClickListener(v -> finish());
        btnEdit.setOnClickListener(v -> editPose());
        btnDelete.setOnClickListener(v -> confirmDelete());
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_delete_title)
                .setMessage(R.string.dialog_delete_message)
                .setPositiveButton(R.string.dialog_delete, (dialog, which) -> deletePose())
                .setNegativeButton(R.string.dialog_cancel, null)
                .show();
    }

    private void deletePose() {
        if (poseId != null) {
            db.collection("poses").document(poseId).delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Postura eliminada con éxito", Toast.LENGTH_SHORT).show();
                        finish(); // Volver a la lista
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error al eliminar la postura", Toast.LENGTH_SHORT).show());
        }
    }

    private void editPose() {
        Intent intent = new Intent(this, AddPoseActivity.class);
        intent.putExtra("isEditing", true);
        intent.putExtra("poseId", poseId);
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        intent.putExtra("imageUrl", imageUrl);
        startActivity(intent);
        // Finalizamos DetailActivity para que al guardar los cambios, volvamos directamente a HomeActivity
        finish();
    }
}
