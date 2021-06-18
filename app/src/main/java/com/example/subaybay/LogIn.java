package com.example.subaybay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.subaybay.ui.Registration;

//TODO
// error trapping
// log in function
// username function
// password function
// regiter navigation
// backend integration
public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private EditText mobileNumber;
    private EditText password;
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        this.loginBtn = findViewById(R.id.loginBtn);
        this.registerBtn = findViewById(R.id.RegisterBtn);
        this.mobileNumber = findViewById(R.id.editTextPhone);
        this.password = findViewById(R.id.editTextPassword);

        this.loginBtn.setOnClickListener(this);
    }
    // onclick listener
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginBtn:
                String mobileNumber = this.mobileNumber.getText().toString();
                String password = this.password.getText().toString();
                if(isCredentialSetIn(mobileNumber,password)){
                    // code here
                }else{
                    // code here
                }

                break;
            case R.id.RegisterBtn:
                Intent intent = new Intent(this, Registration.class);
                startActivity(intent);
                break;
        }
    }

    public boolean isCredentialSetIn(String mobileNumber, String password){
        return ((mobileNumber.equals("") || password.equals("")) || (mobileNumber.length() != 10 || password.length()<=5));
    }
}