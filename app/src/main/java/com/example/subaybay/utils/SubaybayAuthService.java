package com.example.subaybay.utils;

import com.example.subaybay.constants.ConstantVariables;
import com.example.subaybay.dto.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SubaybayAuthService {
    @POST(ConstantVariables.SIGNUP_URL)
    Call<RegisterRequest>sigup(@Body RegisterRequest registerRequest);
}
