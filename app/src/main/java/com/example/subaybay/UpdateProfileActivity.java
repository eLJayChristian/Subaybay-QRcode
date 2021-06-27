package com.example.subaybay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subaybay.dto.UpdateRequest;
import com.example.subaybay.utils.SessionManager;
import com.example.subaybay.utils.SubaybayAPIs;
import com.example.subaybay.utils.SubaybayAuthService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText edtFirstname, edtMiddlename, edtLastname, edtBirthday;
    private CheckBox vaccinatedChecked;
    private Button updateBtn, cancelBtn;
    private Intent extra;
    private SessionManager sessionManager;
    private SubaybayAuthService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        extra = getIntent();

        edtFirstname = findViewById(R.id.firstname_edt);
        edtMiddlename = findViewById(R.id.lastname_edt);
        edtLastname = findViewById(R.id.middlename_edt);

        edtBirthday = findViewById(R.id.birthday_edt);
        vaccinatedChecked = findViewById(R.id.vaccineAuthen);

        updateBtn = findViewById(R.id.update_btn);
        cancelBtn = findViewById(R.id.cancel_btn);

        String firstname = extra.getExtras().getString("firstname");
        String middlename = extra.getExtras().getString("middlename");
        String lastname = extra.getExtras().getString("lastname");
        String birthday = extra.getExtras().getString("birthday");
        boolean vaccinated = extra.getExtras().getBoolean("isVaccinated");
        String password = extra.getExtras().getString("password");
        boolean admin = extra.getExtras().getBoolean("isAdmin");
//        String mobilenumber = extra.getExtras().getString("mobilenumber");

        edtFirstname.setText(firstname);
        edtMiddlename.setText(middlename);
        edtLastname.setText(lastname);

        vaccinatedChecked.setChecked(vaccinated);

        cancelBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });


        updateBtn.setOnClickListener(v -> {
            sessionManager = new SessionManager(getApplicationContext());
            service = SubaybayAPIs.subaybayAuthService();

            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.setFirstname(edtFirstname.getText().toString());
            updateRequest.setMiddlename(edtMiddlename.getText().toString());
            updateRequest.setLastname(edtLastname.getText().toString());

            updateRequest.setBirthday(edtBirthday.getText().toString().isEmpty()? birthday : edtBirthday.getText().toString());

            updateRequest.setVaccinated(vaccinatedChecked.isChecked());
            updateRequest.setMobileNumber(Long.parseLong(sessionManager.mobileNumber()));
            updateRequest.setPassword(password);
            updateRequest.setAdmin(admin);

            Call<ResponseBody> call = service.updateUser("Bearer "+sessionManager.token(), Long.parseLong(sessionManager.mobileNumber()), updateRequest);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(UpdateProfileActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Error: ", t.getMessage());
                }
            });
        });

    }
}