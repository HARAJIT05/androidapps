package com.example.add;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    public void add(View v){
        EditText fno=(EditText) findViewById(R.id.edtInput1);
        EditText sno=(EditText) findViewById(R.id.edtInput2);
        TextView result=(TextView) findViewById(R.id.result);
        Integer f =Integer.parseInt(fno.getText().toString());
        Integer s =Integer.parseInt(sno.getText().toString());
        Integer r =f+s;
        result.setText(r.toString());
    }
}