package com.example.subaybay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.subaybay.utils.SubaybayAPIs;
import com.example.subaybay.utils.SubaybayAuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verification extends AppCompatActivity implements View.OnClickListener {
    private Button verify;
    private EditText verificationCode;
    private SubaybayAuthService service;
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
                String verificationCode = this.verificationCode.getText().toString();
                if(verificationCode.equals("")){
                    Toast.makeText(this, "We sent a verification code please input the code to verify.", Toast.LENGTH_SHORT).show();
                }
                else{
                    this.service = SubaybayAPIs.subaybayAuthServicePlainString();; // initialized first
                    Call<String> call = this.service.verifyUser(Integer.parseInt(verificationCode));
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.code() == 201 || response.code() == 200){

                                Toast.makeText(Verification.this, response.message(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Verification.this,LogIn.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("eto Error Junie:",t.getMessage());
                        }
                    });
                    // send request to the server

                }

                break;
        }
    }
}