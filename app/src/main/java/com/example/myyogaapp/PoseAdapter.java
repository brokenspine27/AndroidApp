package com.example.myyogaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PoseAdapter extends RecyclerView.Adapter<PoseAdapter.PoseViewHolder> {

    private List<Pose> poses;

    public PoseAdapter(List<Pose> poses) {
        this.poses = poses;
    }

    @NonNull
    @Override
    public PoseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pose, parent, false);
        return new PoseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoseViewHolder holder, int position) {
        Pose pose = poses.get(position);
        holder.tvTitle.setText(pose.getTitle());
        holder.tvDescription.setText(pose.getDescription());

        // Convertir el nombre de la imagen (String) a un ID de recurso (int)
        Context context = holder.itemView.getContext();
        String imageName = pose.getImageName();
        if (imageName != null && !imageName.isEmpty()) {
            int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
            if (imageResId != 0) { // 0 significa que no se encontró el recurso
                holder.ivPose.setImageResource(imageResId);
            }
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("title", pose.getTitle());
            intent.putExtra("description", pose.getDescription());
            intent.putExtra("imageName", pose.getImageName()); // Enviamos el nombre de la imagen, no el ID
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return poses.size();
    }

    static class PoseViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPose;
        TextView tvTitle;
        TextView tvDescription;

        public PoseViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPose = itemView.findViewById(R.id.iv_pose);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}
