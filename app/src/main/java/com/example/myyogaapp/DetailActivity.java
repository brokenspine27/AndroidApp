package com.example.myyogaapp;

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
        int image = getIntent().getIntExtra("image", 0);

        ivPose.setImageResource(image);
        tvTitle.setText(title);
        tvDescription.setText(description);

        btnBack.setOnClickListener(v -> finish());
    }
}
