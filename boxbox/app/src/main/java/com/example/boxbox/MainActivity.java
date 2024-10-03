package com.example.boxbox;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void show(View v) {
        ImageView i1 = findViewById(R.id.image1);
        ImageView i2 = findViewById(R.id.image2);
        ImageView i3 = findViewById(R.id.image3);
        ImageView i4 = findViewById(R.id.imageView4);

        RadioButton rb1 = findViewById(R.id.rb1);
        RadioButton rb2 = findViewById(R.id.rb2);
        RadioButton rb3 = findViewById(R.id.rb3);

        CheckBox cb = findViewById(R.id.cb);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (rb1.isChecked()) {
            i4.setImageDrawable(i1.getDrawable());
            mediaPlayer = MediaPlayer.create(this, R.raw.dududu);
        } else if (rb2.isChecked()) {
            i4.setImageDrawable(i2.getDrawable());
            mediaPlayer = MediaPlayer.create(this, R.raw.smoothop);
        } else if (rb3.isChecked()) {
            i4.setImageDrawable(i3.getDrawable());
            mediaPlayer = MediaPlayer.create(this, R.raw.landooo);
        } else {
            Toast t = Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT);
            t.show();
            return;
        }


        if (mediaPlayer != null) {
            mediaPlayer.start();
        }


        if (cb.isChecked()) {
            i4.setVisibility(View.VISIBLE);
        } else {
            i4.setVisibility(View.INVISIBLE);
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
