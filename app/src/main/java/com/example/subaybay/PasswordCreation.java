package com.example.subaybay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.subaybay.ui.Registration;

public class PasswordCreation extends AppCompatActivity implements View.OnClickListener{
    private Button nextBtn, backBtn;
    private EditText password, confirmPassword,mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_creation);

        //intent

        //button
        nextBtn = findViewById(R.id.NxtBtn1);
        backBtn = findViewById(R.id.BackBtn1);

        // edit text
        password = findViewById(R.id.Password);
        confirmPassword = findViewById(R.id.passConfirm);
        mobileNumber = findViewById(R.id.phoneNum);

        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.NxtBtn1:
                if(true){
                    //check all then retrofit then send a request to server then verification code
                    Intent intent = new Intent(this, Verification.class);
                    // putExtra here
                    startActivity(intent);
                }
                break;
            case R.id.BackBtn1:
                Intent intent = new Intent(this, Registration.class);
                // putExtra here
                startActivity(intent);
                break;
        }
    }
}