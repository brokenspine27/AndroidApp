package com.example.myyogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
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
    private Button btnLogout;
    private FirebaseAuth mAuth;
    private static boolean migrationAttempted = false; // Flag para controlar la migración


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        rvPoses = findViewById(R.id.rv_poses);
        btnLogout = findViewById(R.id.btn_logout);
        rvPoses.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PoseAdapter(poses);
        rvPoses.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_pose);
        fab.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, AddPoseActivity.class)));

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPoses();
    }

    private void loadPoses() {
        db.collection("poses")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().isEmpty() && !migrationAttempted) {
                            Log.d(TAG, "Pose collection is empty. Migrating initial data.");
                            migrateDataAndReload();
                        } else {
                            migrationAttempted = true;
                            Log.d(TAG, "Found " + task.getResult().size() + " poses in Firestore. Populating view.");
                            poses.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Convertir el documento a un objeto Pose
                                Pose pose = document.toObject(Pose.class);
                                // Guardar el ID del documento en el objeto
                                pose.setId(document.getId());
                                poses.add(pose);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    private void migrateDataAndReload() {
        migrationAttempted = true; // Marcar que la migración se ha intentado
        List<Pose> initialPoses = new ArrayList<>();
        initialPoses.add(new Pose("Vrksasana", "Una postura de equilibrio fundamental.", "https://www.yogajournal.com/wp-content/uploads/2021/11/Tree-Pose_Andrew-Clark_1.jpg"));
        initialPoses.add(new Pose("Virabhadrasana I", "Fortalece las piernas y el tronco.", "https://www.yogajournal.com/wp-content/uploads/2021/10/Warrior-I-Pose_Andrew-Clark_1.jpg"));

        List<Task<?>> migrationTasks = new ArrayList<>();
        for (Pose pose : initialPoses) {
            migrationTasks.add(db.collection("poses").add(pose));
        }

        Tasks.whenAll(migrationTasks)
                .addOnSuccessListener(aVoid -> {
                    Log.d(TAG, "Initial data migration successful. Reloading data from Firestore.");
                    // En lugar de añadir la lista local, volvemos a cargar desde Firestore para obtener los IDs
                    loadPoses();
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error during initial data migration.", e);
                    migrationAttempted = false; // Si falla, permitir que se intente de nuevo más tarde
                });
    }
}
