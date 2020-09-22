package com.zizoabdohisinymohamed.calculator.UiActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zizoabdohisinymohamed.calculator.R;
import com.zizoabdohisinymohamed.calculator.UiActivity.MainActivity;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        // Delay For 1 Seconde
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent Go The Main Acticity

                Intent in=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
                finish();
            }
        },1500);
    }
}
