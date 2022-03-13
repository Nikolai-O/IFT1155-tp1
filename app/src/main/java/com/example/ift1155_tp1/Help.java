package com.example.ift1155_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }

    public void back (View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
