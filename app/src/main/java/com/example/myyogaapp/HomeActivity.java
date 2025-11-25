package com.example.myyogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private RecyclerView rvPoses;
    private PoseAdapter adapter;
    private List<Pose> poses = new ArrayList<>();
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = FirebaseFirestore.getInstance();
        rvPoses = findViewById(R.id.rv_poses);
        rvPoses.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PoseAdapter(poses);
        rvPoses.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_pose);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AddPoseActivity.class));
            }
        });

        fetchPosesFromFirestore();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualiza la lista cuando volvemos de AddPoseActivity
        fetchPosesFromFirestore();
    }

    private void fetchPosesFromFirestore() {
        db.collection("poses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            poses.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Pose pose = document.toObject(Pose.class);
                                poses.add(pose);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    /**
     * Este es un método de un solo uso para migrar los datos iniciales a Firestore.
     * Después de ejecutar la app una vez con este método activo, coméntalo o elimínalo.
     */
    private void migrateDataToFirestore() {
        List<Pose> initialPoses = new ArrayList<>();
        initialPoses.add(new Pose("Vrksasana", "Una postura de equilibrio fundamental.", "ic_tree_pose"));
        initialPoses.add(new Pose("Virabhadrasana I", "Fortalece las piernas y el tronco.", "ic_warrior_pose"));

        for (Pose pose : initialPoses) {
            db.collection("poses").add(pose)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "Pose añadida con ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error al añadir la pose", e));
        }
    }
}
