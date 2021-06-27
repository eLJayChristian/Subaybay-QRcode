package com.example.subaybay;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.subaybay.dto.RegisterRequest;
import com.example.subaybay.ui.Registration;
import com.example.subaybay.utils.SubaybayAPIs;
import com.example.subaybay.utils.SubaybayAuthService;


import org.joda.time.LocalDate;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordCreation extends AppCompatActivity implements View.OnClickListener{
    private Button nextBtn, backBtn;
    private EditText password, confirmPassword,mobileNumber;
    private Intent extra;
    private SubaybayAuthService subaybayAuthService;

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

        this.extra = getIntent();
    }

    private boolean correctPassword(String password, String confirmPassword){
        if(password.equals("") || password.length()<=5) {
            Toast.makeText(this, "please put a valid password and atleast 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!password.equals(confirmPassword)) {
            Toast.makeText(this, "password and confirm password not matched", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SneakyThrows
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.NxtBtn1:

//                intent.putExtra("firstname",firstname);
//                intent.putExtra("middlename",middlename);
//                intent.putExtra("lastname",lastname);
//                intent.putExtra("birthday",birthday);
//                intent.putExtra("region",region);
//                intent.putExtra("province",province);
//                intent.putExtra("city",city);
//                intent.putExtra("barangay",barangay);
//                intent.putExtra("vaccinated", isVaccinated);

                String password = this.password.getText().toString();
                String confirmPassword = this.confirmPassword.getText().toString();

                String mobileNumber = this.mobileNumber.getText().toString();

                if(correctPassword(password, confirmPassword) && correctMobile(mobileNumber)){
                    RegisterRequest registerRequest = new RegisterRequest();
                    // string to date
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.TAIWAN);
//                    java.util.Date bday = sdf.parse(extra.getExtras().getString("birthday"));
//                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//                    String dateofbirth = f.format(bday);
//                    java.util.Date bday1 = f.parse(dateofbirth);
//                    java.sql.Date birthday = new Date(bday1.getTime());
//                    java.sql.Date birthday = bdayTimestamp;
//                    LocalDate birthday = LocalDate.parse(extra.getExtras().getString("birthday"));
                    // user info
                    registerRequest.setFirstname(extra.getExtras().getString("firstname"));
                    registerRequest.setMiddlename(extra.getExtras().getString("middlename"));
                    registerRequest.setLastname(extra.getExtras().getString("lastname"));
                    registerRequest.setVaccinated(extra.getExtras().getBoolean("vaccinated"));
                    registerRequest.setAdmin(false);
                    registerRequest.setMobileNumber(Long.parseLong(mobileNumber));
                    registerRequest.setPassword(password);
                    // address and birthday
                    registerRequest.setBirthday(extra.getExtras().getString("birthday"));
                    registerRequest.setRegion(extra.getExtras().getString("region"));
                    registerRequest.setProvince(extra.getExtras().getString("province"));
                    registerRequest.setCity(extra.getExtras().getString("city"));
                    registerRequest.setBarangay(extra.getExtras().getString("barangay"));
                    // image
                    registerRequest.setImageName("app logo");
                    registerRequest.setPath("@mipmap/subaybaylogo");





                    //check all then retrofit then send a request to server then verification code
                    subaybayAuthService = SubaybayAPIs.subaybayAuthService();
                    Call<ResponseBody> call = subaybayAuthService.signup(registerRequest);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                // code something here
                            }
                            if(response.code() == 201){
                                System.out.println("dumaan ako dito sa Response");
                                System.out.println(response.body());
                                Toast.makeText(PasswordCreation.this, "please verify your account", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PasswordCreation.this, Verification.class);
                                startActivity(intent);
                                finish();
                            }
                            if(response.code() == 302){
                                Toast.makeText(PasswordCreation.this, "This Phone Number is already exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(PasswordCreation.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("Error:",t.getMessage());
                        }
                    });

                }
                else{
                    Toast.makeText(this, "Please check your credentials you might miss something", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.BackBtn1:
                Intent intent = new Intent(this, Registration.class);
                // putExtra here
                startActivity(intent);
                break;
        }
    }

    private boolean correctMobile(String mobileNumber) {
        char firstDigit = mobileNumber.charAt(0);
        if(mobileNumber.equals("") || mobileNumber.length()<=9) {
            Toast.makeText(this, "mobile number must be 10 digit.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(firstDigit == '0') {
            Toast.makeText(this, "you don't need to put the zero as a first digit. we only need 10 digit", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}