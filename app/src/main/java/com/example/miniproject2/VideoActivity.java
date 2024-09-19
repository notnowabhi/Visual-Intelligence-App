package com.example.miniproject2;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> captureVideoLauncher;
    private ActivityResultLauncher<String> pickVideoLauncher;
    private ArrayList<Uri> videoUris = new ArrayList<>();
    private VideoAdapter videoAdapter;
    private RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Request camera permission if needed
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        parent = findViewById(R.id.parent); // for the snackbar.

        showSnackbar(); // POPS UP THE SNACKBAR.

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.videoRecyclerView);
        videoAdapter = new VideoAdapter(videoUris, this);
        recyclerView.setAdapter(videoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up capture video launcher
        captureVideoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Uri videoUri = result.getData().getData();
                if (videoUri != null) {
                    videoUris.add(videoUri);
                    videoAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Video captured!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up pick video launcher
        pickVideoLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                videoUris.add(uri);
                videoAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Video selected!", Toast.LENGTH_SHORT).show();
            }
        });

        // Capture video button
        Button captureButton = findViewById(R.id.captureVideoButton);
        captureButton.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            captureVideoLauncher.launch(intent);
        });

        // Upload video button
        Button uploadButton = findViewById(R.id.uploadVideoButton);
        uploadButton.setOnClickListener(v -> {
            pickVideoLauncher.launch("video/*");
        });
    }

    private void showSnackbar(){
        Snackbar.make(parent, "THIS IS UPLOAD/RECORD SCREEN", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(VideoActivity.this, "HAPPY GENERATING CAPTIONS :)", Toast.LENGTH_SHORT).show();
                    }
                })
                .setTextColor(ContextCompat.getColor(this, R.color.purple))
                .show();
    }
}
