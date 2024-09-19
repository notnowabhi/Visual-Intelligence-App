package com.example.miniproject2;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoPlaybackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);

        VideoView videoView = findViewById(R.id.fullscreenVideoView);
        String videoUriString = getIntent().getStringExtra("videoUri");
        Uri videoUri = Uri.parse(videoUriString);

        videoView.setVideoURI(videoUri);
        videoView.start();
    }
}
