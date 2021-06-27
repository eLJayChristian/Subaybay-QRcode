package com.example.subaybay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String birthday;
    private long mobileNumber;
    @SerializedName("vaccinated")
    @Expose
    private boolean isVaccinated;
    private String password;
    private String dateRegistered;
    private boolean isVerified;
    private boolean isAdmin;
}
