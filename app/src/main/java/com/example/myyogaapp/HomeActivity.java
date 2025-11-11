package com.example.myyogaapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rvPoses = findViewById(R.id.rv_poses);
        rvPoses.setLayoutManager(new LinearLayoutManager(this));

        List<Pose> poses = new ArrayList<>();
        // Add sample data
        poses.add(new Pose("Vrksasana", "Una postura de equilibrio fundamental.", R.drawable.ic_tree_pose));
        poses.add(new Pose("Virabhadrasana I", "Fortalece las piernas y el tronco.", R.drawable.ic_warrior_pose));

        PoseAdapter adapter = new PoseAdapter(poses);
        rvPoses.setAdapter(adapter);
    }
}
