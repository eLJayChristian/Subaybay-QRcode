package com.example.subaybay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Verification extends AppCompatActivity implements View.OnClickListener {
    private Button verify;
    private EditText verificationCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        verify = findViewById(R.id.verifyBtn);
        verificationCode = findViewById(R.id.authenticationCode);

        verify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.verifyBtn:
                // send request to the server
                Intent intent = new Intent(this,LogIn.class);
                startActivity(intent);
                break;
        }
    }
}