package com.example.miniproject2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final ArrayList<Uri> videoUris;
    private final Context context;

    public VideoAdapter(ArrayList<Uri> videoUris, Context context) {
        this.videoUris = videoUris;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Uri videoUri = videoUris.get(position);
        holder.videoView.setVideoURI(videoUri);
        holder.videoView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoPlaybackActivity.class);
            intent.putExtra("videoUri", videoUri.toString());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoUris.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }
}
