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
import com.bumptech.glide.Glide;
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

        // Usar Glide para cargar la imagen desde la URL
        Context context = holder.itemView.getContext();
        String imageUrl = pose.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.ivPose);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            // Añadir el ID de la postura al intent
            intent.putExtra("poseId", pose.getId());
            intent.putExtra("title", pose.getTitle());
            intent.putExtra("description", pose.getDescription());
            intent.putExtra("imageUrl", pose.getImageUrl());
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
