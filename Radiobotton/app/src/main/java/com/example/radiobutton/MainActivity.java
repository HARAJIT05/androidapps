package com.example.radiobutton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
    public void show(View v){
        ImageView img=(ImageView)findViewById(R.id.imgview);
        RadioButton Max=(RadioButton)findViewById(R.id.rbtnMax);
        RadioButton Carlos=(RadioButton)findViewById(R.id.rbtnCarlos);
        RadioButton Kimi=(RadioButton)findViewById(R.id.rbtnKimi);
        RadioButton Charls=(RadioButton)findViewById(R.id.rbtnCharls);

        if (Max.isChecked()){
            img.setImageResource(R.drawable.max);
            }
        else if (Carlos.isChecked()){
            img.setImageResource(R.drawable.carlos);
        }
        else if (Kimi.isChecked()){
            img.setImageResource(R.drawable.kimi);
        }
        else if (Charls.isChecked()){
            img.setImageResource(R.drawable.charls);
        }
    }
}