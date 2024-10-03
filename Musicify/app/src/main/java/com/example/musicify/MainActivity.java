package com.example.musicify;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_AUDIO_REQUEST = 1;
    private MediaPlayer mediaPlayer;
    private ImageView playPauseBtn;
    private SeekBar seekBar;
    private TextView songTitle, artistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request storage permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            selectMusicFile();
        }
    }

    private void selectMusicFile() {
        // Intent to pick an audio file
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent, PICK_AUDIO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri audioUri = data.getData();
            setupMusicPlayer(audioUri);
        }
    }

    private void setupMusicPlayer(Uri audioUri) {
        // Get UI components
        playPauseBtn = findViewById(R.id.btnPlayPause);
        seekBar = findViewById(R.id.seekBar);
        songTitle = findViewById(R.id.songTitle);
        artistName = findViewById(R.id.artistName);

        // Set song title and artist (you can modify this to extract from metadata if needed)
        songTitle.setText("Selected Song");  // Placeholder
        artistName.setText("Artist Name");     // Placeholder

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(this, audioUri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error preparing media player", Toast.LENGTH_SHORT).show();
        }

        // Play/Pause functionality
        playPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playPauseBtn.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    playPauseBtn.setImageResource(R.drawable.pause);
                }
            }
        });

        // SeekBar progress update
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mediaPlayer.getDuration());
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playPauseBtn.setImageResource(R.drawable.play);
            }
        });
    }

    // Request permission result handling
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectMusicFile();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
