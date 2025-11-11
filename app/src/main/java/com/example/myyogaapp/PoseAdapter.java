package com.example.myyogaapp;

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

        if (pose.isFromResource()) {
            holder.ivPose.setImageResource(pose.getImageResourceId());
        } else {
            holder.ivPose.setImageURI(pose.getImageUri());
        }

        holder.tvTitle.setText(pose.getTitle());
        holder.tvDescription.setText(pose.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("title", pose.getTitle());
            intent.putExtra("description", pose.getDescription());
            if (pose.isFromResource()) {
                intent.putExtra("image_res_id", pose.getImageResourceId());
            } else {
                intent.putExtra("image_uri", pose.getImageUri().toString());
            }
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
