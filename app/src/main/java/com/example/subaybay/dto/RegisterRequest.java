package com.example.subaybay.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import org.joda.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//{
//        "firstname": "heyeus",
//        "middlename": "Cala",
//        "lastname":"Delos Reyes",
//        "birthday": "1980-04-09T10:15:30Z",
//        "mobileNumber":"9354679565",
//        "isVaccinated":"false",
//        "password":"123456",
//        "isAdmin" : "true",
//        "region":"3",
//        "province":"bulacan",
//        "city":"bulacan",
//        "barangay":"sannicolas",
//        "imageName":"juniepicture",
//        "path":"/junie/folder.jpg"
//        }
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("middlename")
    @Expose
    private String middlename;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("mobileNumber")
    @Expose
    private long mobileNumber;

    @SerializedName("vaccinated")
    @Expose
    private boolean isVaccinated;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("admin")
    @Expose
    private boolean isAdmin;

    @SerializedName("region")
    @Expose
    private String region;

    @SerializedName("province")
    @Expose
    private String province;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("barangay")
    @Expose
    private String barangay;

    @SerializedName("imageName")
    @Expose
    private String imageName;

    @SerializedName("path")
    @Expose
    private String path;


}
