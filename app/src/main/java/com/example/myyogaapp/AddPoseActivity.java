package com.example.myyogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddPoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pose);

        EditText etTitle = findViewById(R.id.et_pose_title);
        EditText etDescription = findViewById(R.id.et_pose_description);
        Button btnSave = findViewById(R.id.btn_save_pose);

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("new_title", etTitle.getText().toString());
            resultIntent.putExtra("new_description", etDescription.getText().toString());
            // For simplicity, we'll use a placeholder image for now
            resultIntent.putExtra("new_image", R.drawable.ic_om_yoga);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
