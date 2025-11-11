package com.example.myyogaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private PoseAdapter adapter;
    private List<Pose> poses;
    private ActivityResultLauncher<Intent> addPoseLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rvPoses = findViewById(R.id.rv_poses);
        rvPoses.setLayoutManager(new LinearLayoutManager(this));

        poses = new ArrayList<>();
        // Add sample data
        poses.add(new Pose("Pose del Árbol", "Una postura de equilibrio fundamental.", R.drawable.ic_tree_pose));
        poses.add(new Pose("Pose del Guerrero", "Fortalece las piernas y el tronco.", R.drawable.ic_warrior_pose));

        adapter = new PoseAdapter(poses);
        rvPoses.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_pose);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddPoseActivity.class);
                addPoseLauncher.launch(intent);
            }
        });

        addPoseLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String title = data.getStringExtra("new_title");
                        String description = data.getStringExtra("new_description");
                        String imageUriString = data.getStringExtra("new_image_uri");

                        if (imageUriString != null) {
                            Uri imageUri = Uri.parse(imageUriString);
                            poses.add(new Pose(title, description, imageUri));
                            adapter.notifyItemInserted(poses.size() - 1);
                        }
                    }
                });
    }
}
