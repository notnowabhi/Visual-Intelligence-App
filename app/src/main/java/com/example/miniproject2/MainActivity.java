package com.example.miniproject2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);

        // Button to navigate to VideoActivity
        Button navigateButton = findViewById(R.id.navigateVideoActivityButton);
        navigateButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
            startActivity(intent);
        });

        showSnackbar(); //shows a snackbar at bottom


    }

    private void showSnackbar(){
        Snackbar.make(parent, "THIS IS MAIN SCREEN", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "HAPPY GENERATING CAPTIONS :)", Toast.LENGTH_SHORT).show();
                    }
                })
                .setTextColor(ContextCompat.getColor(this, R.color.purple))
                .show();
    }

}
