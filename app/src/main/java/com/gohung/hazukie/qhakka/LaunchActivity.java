package com.gohung.hazukie.qhakka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (intent == null) {
            intent = new Intent(this, mainAllAct.class);
        }
        startActivity(intent);
        finish();
    }
    }
