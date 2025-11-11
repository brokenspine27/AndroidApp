package com.example.myyogaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class AddPoseActivity extends AppCompatActivity {

    private ImageView ivPosePreview;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivPosePreview.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pose);

        ivPosePreview = findViewById(R.id.iv_pose_preview);
        EditText etTitle = findViewById(R.id.et_pose_title);
        EditText etDescription = findViewById(R.id.et_pose_description);
        Button btnSelectImage = findViewById(R.id.btn_select_image);
        Button btnSave = findViewById(R.id.btn_save_pose);
        Button btnBack = findViewById(R.id.btn_back_add_pose);

        btnSelectImage.setOnClickListener(v -> mGetContent.launch("image/*"));

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("new_title", etTitle.getText().toString());
            resultIntent.putExtra("new_description", etDescription.getText().toString());
            if (selectedImageUri != null) {
                resultIntent.putExtra("new_image_uri", selectedImageUri.toString());
            }
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
