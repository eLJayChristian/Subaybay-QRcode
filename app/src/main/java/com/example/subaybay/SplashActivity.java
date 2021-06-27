package com.example.subaybay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.example.subaybay.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {
    private TextView text12, text13;
    private Button skipBtn;
    private SessionManager sessionManager;

    // [noun] keep track of, observe closely

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.text12 = findViewById(R.id.textView12);
        this.text13 = findViewById(R.id.textView13);
        sessionManager = new SessionManager(this);

        skipBtn = findViewById(R.id.buttonSkip);

        skipBtn.setOnClickListener(v -> sessionManager.alreadyLogIn());

        new Handler().postDelayed(() -> text12.setText("["), 300);

        new Handler().postDelayed(() -> text12.append("n"), 400);

        new Handler().postDelayed(() -> text12.append("o"), 500);
        new Handler().postDelayed(() -> text12.append("u"), 600);
        new Handler().postDelayed(() -> text12.append("n"), 700);
        new Handler().postDelayed(() -> text12.append("]"), 800);

        new Handler().postDelayed(() -> text13.setText("k"), 900);
        new Handler().postDelayed(() -> text13.append("e"), 1000);
        new Handler().postDelayed(() -> text13.append("e"), 1100);
        new Handler().postDelayed(() -> text13.append("p "), 1200);

        new Handler().postDelayed(() -> text13.append("t"), 1300);
        new Handler().postDelayed(() -> text13.append("r"), 1400);
        new Handler().postDelayed(() -> text13.append("a"), 1500);
        new Handler().postDelayed(() -> text13.append("c"), 1600);
        new Handler().postDelayed(() -> text13.append("k "), 1700);

        new Handler().postDelayed(() -> text13.append("o"), 1800);
        new Handler().postDelayed(() -> text13.append("f"), 1900);
        new Handler().postDelayed(() -> text13.append(", "), 2000);

        new Handler().postDelayed(() -> text13.append("o"), 2100);
        new Handler().postDelayed(() -> text13.append("b"), 2200);
        new Handler().postDelayed(() -> text13.append("s"), 2300);
        new Handler().postDelayed(() -> text13.append("e"), 2400);
        new Handler().postDelayed(() -> text13.append("r"), 2500);
        new Handler().postDelayed(() -> text13.append("v"), 2600);
        new Handler().postDelayed(() -> text13.append("e "), 2700);

        new Handler().postDelayed(() -> text13.append("c"), 2800);
        new Handler().postDelayed(() -> text13.append("l"), 2900);
        new Handler().postDelayed(() -> text13.append("o"), 3000);
        new Handler().postDelayed(() -> text13.append("s"), 3100);
        new Handler().postDelayed(() -> text13.append("e"), 3200);
        new Handler().postDelayed(() -> text13.append("l"), 3300);
        new Handler().postDelayed(() -> text13.append("y"), 3400);

        new Handler().postDelayed(() -> sessionManager.alreadyLogIn(), 4000);
        new Handler().postDelayed(() -> finish(), 4000);


    }
}