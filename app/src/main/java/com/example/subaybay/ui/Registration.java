package com.example.subaybay.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.subaybay.LogIn;
import com.example.subaybay.PasswordCreation;
import com.example.subaybay.R;

import java.util.Date;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private Button nextBtn, backBtn;
    private EditText firstname, middlename, lastname, birthday;
    private EditText region, province, city, barangay;
    private CheckBox vaccinated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // buttons
        this.nextBtn = findViewById(R.id.NxtBtn1);
        this.backBtn = findViewById(R.id.BackBtn1);

        this.nextBtn.setOnClickListener(this);
        this.backBtn.setOnClickListener(this);

        //person's name
        this.firstname = findViewById(R.id.FName);
        this.lastname = findViewById(R.id.LName);
        this.middlename = findViewById(R.id.MName);

        // birthday
        this.birthday = findViewById(R.id.Bday);

        // person's address
        this.region =  findViewById(R.id.regionEdttxt);
        this.province = findViewById(R.id.provinceEdttxt);
        this.city = findViewById(R.id.City);
        this.barangay = findViewById(R.id.Baranggay);

        // isVaccinated
        this.vaccinated = findViewById(R.id.vaccineAuthen);

    }

    // firstname
    // middlename
    // lastname
    // birthday
    // region
    // province
    // city
    // barangay

    private boolean isCredentialSetIn(String firstname, String middlename, String lastname, String birthday,
                                      String region, String province, String city, String barangay){
//|| !regexPattern(firstname)
        if(firstname.equals(""))
            return false;

        else if(middlename.equals(""))
            return false;

        else if(lastname.equals(""))
            return false;

        else if(birthday.equals(""))
            return false;

        else if(region.equals(""))
            return false;

        else if(province.equals(""))
            return false;

        else if(city.equals(""))
            return false;

        else if(barangay.equals(""))
            return false;

        return true;
    }

    public boolean regexPattern(String str) {
        return str.matches( "[A-Z][a-z]*" );
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.NxtBtn1:

                String firstname = this.firstname.getText().toString();
                String middlename = this.middlename.getText().toString();
                String lastname = this.lastname.getText().toString();
                String birthday = this.birthday.getText().toString();
                String region = this.region.getText().toString();
                String province = this.province.getText().toString();
                String city = this.city.getText().toString();
                String barangay = this.barangay.getText().toString();
                boolean isVaccinated = this.vaccinated.isChecked();

                boolean isFillup = isCredentialSetIn(firstname,middlename,lastname
                                                    ,birthday,region,province,city,barangay);

                if(isFillup){
                    Intent intent = new Intent(this, PasswordCreation.class);

                    intent.putExtra("firstname",firstname);
                    intent.putExtra("middlename",middlename);
                    intent.putExtra("lastname",lastname);
                    intent.putExtra("birthday",birthday);
                    intent.putExtra("region",region);
                    intent.putExtra("province",province);
                    intent.putExtra("city",city);
                    intent.putExtra("barangay",barangay);
                    intent.putExtra("vaccinated", isVaccinated);

                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Please complete and don't unnecessary numbers in your name", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.BackBtn1:
                Intent intent = new Intent(this, LogIn.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}