package com.example.myyogaapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ivPose = findViewById(R.id.iv_pose_detail);
        TextView tvTitle = findViewById(R.id.tv_title_detail);
        TextView tvDescription = findViewById(R.id.tv_description_detail);
        Button btnBack = findViewById(R.id.btn_back);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        if (getIntent().hasExtra("image_res_id")) {
            int imageResId = getIntent().getIntExtra("image_res_id", 0);
            ivPose.setImageResource(imageResId);
        } else if (getIntent().hasExtra("image_uri")) {
            String imageUriString = getIntent().getStringExtra("image_uri");
            ivPose.setImageURI(Uri.parse(imageUriString));
        }

        tvTitle.setText(title);
        tvDescription.setText(description);

        btnBack.setOnClickListener(v -> finish());
    }
}
