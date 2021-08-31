package com.example.covidshield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Simple extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Thread thread = new Thread(){
          public void run(){
              try{
                  sleep(4000);
              }
              catch (Exception e){
                  e.printStackTrace();
              }
              finally {
                  Intent intent = new Intent(Simple.this,MainActivity.class);
                  startActivity(intent);
              }
          }
        };thread.start();
    }
}