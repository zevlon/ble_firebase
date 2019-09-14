package com.example.zevlon.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = (Button) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, child_details.class);
                startActivity(myintent);
                }
        });
    }
}
