package com.example.subaybay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subaybay.dto.LogInResponse;
import com.example.subaybay.dto.LoginRequest;
import com.example.subaybay.ui.Registration;
import com.example.subaybay.utils.SessionManager;
import com.example.subaybay.utils.SubaybayAPIs;
import com.example.subaybay.utils.SubaybayAuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO
// error trapping
// log in function
// username function
// password function
// regiter navigation
// backend integration
public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private SubaybayAuthService service;
    private Button loginBtn;
    private EditText mobileNumber;
    private EditText password;
    private Button registerBtn;
    private SessionManager sessionManager;
    private TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        this.loginBtn = findViewById(R.id.loginBtn);
        this.registerBtn = findViewById(R.id.RegisterBtn);
        this.mobileNumber = findViewById(R.id.editTextPhone);
        this.password = findViewById(R.id.editTextPassword);
        this.forgotPassword = findViewById(R.id.ForgotpassBtn);

        this.loginBtn.setOnClickListener(this);
        this.registerBtn.setOnClickListener(this);

        this.sessionManager = new SessionManager(this);

    }
    // onclick listener
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginBtn:
                String mobileNumber = this.mobileNumber.getText().toString();
                String password = this.password.getText().toString();
                if(isCredentialSetIn(mobileNumber,password)){

                    Toast.makeText(this, "Please input your credentials and your password must be 6 or more characters", Toast.LENGTH_SHORT).show();

                }else{
                    // code here
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setMobileNumber(mobileNumber);
                    loginRequest.setPasssword(password);

                    this.service = SubaybayAPIs.subaybayAuthService();

                    Call<LogInResponse> call = service.login(loginRequest);
                    call.enqueue(new Callback<LogInResponse>() {
                        @Override
                        public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                            if(response.isSuccessful()){

                                LogInResponse logInResponse = new LogInResponse();
                                logInResponse.setAuthenticationToken(response.body().getAuthenticationToken());
                                logInResponse.setMobileNumber(response.body().getMobileNumber());
                                logInResponse.setRefreshToken(response.body().getRefreshToken());
                                logInResponse.setExpiresAt(response.body().getExpiresAt());

                                // session here
                                sessionManager.createLoginSession(logInResponse);
                                Intent intent = new Intent(LogIn.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }else if(response.code() == 403){
                                Toast.makeText(LogIn.this, "Your credentials is not correct", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LogInResponse> call, Throwable t) {
                            Toast.makeText(LogIn.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("Error:",t.getMessage()+" might be your credentials is not correct");
                        }
                    });


                }

                break;

            case R.id.RegisterBtn:
                Intent intent = new Intent(this, Registration.class);
                startActivity(intent);
                break;

            case R.id.ForgotpassBtn:
                Toast.makeText(this, "This feature is currently disable, We are currently limited to send message.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean isCredentialSetIn(String mobileNumber, String password){
        return ((mobileNumber.equals("") || password.equals("")) || (mobileNumber.length() != 10 || password.length()<=5));
    }
}