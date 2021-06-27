package com.example.subaybay.utils;

import com.example.subaybay.constants.ConstantVariables;
import com.example.subaybay.dto.LogInResponse;
import com.example.subaybay.dto.LoginRequest;
import com.example.subaybay.dto.RefreshTokenRequest;
import com.example.subaybay.dto.RegisterRequest;
import com.example.subaybay.dto.ScannedByUserByEstablisment;
import com.example.subaybay.dto.UpdateRequest;
import com.example.subaybay.model.Address;
import com.example.subaybay.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SubaybayAuthService {

    @POST(ConstantVariables.SIGNUP_URL)
    Call<ResponseBody>signup(@Body RegisterRequest registerRequest);

    @GET(ConstantVariables.VERIFY_URL)
    Call<String> verifyUser(@Path("otp") int otp);

    @POST(ConstantVariables.LOGIN_URL)
    Call<LogInResponse> login(@Body LoginRequest loginRequest);

    @POST(ConstantVariables.REFRESHTOKEN_URL)
    Call<LogInResponse> refreshToken(@Body RefreshTokenRequest refreshTokenRequest);

    @GET(ConstantVariables.GETUSERPROFILE_URL)
    Call<User> getUserProfile(@Header("Authorization") String token, @Path("mobileNumber") long mobileNumber);

    @POST(ConstantVariables.LOGOUT_URL)
    Call<ResponseBody> logout(@Body RefreshTokenRequest refreshTokenRequest);

    @GET(ConstantVariables.GETALLUSERADDRESS_URL)
    Call<List<Address>> getAllUserAddress(@Header("Authorization") String token, @Path("userid") long userid);

    @GET(ConstantVariables.GETUSERHISTORY_URL)
    Call<List<ScannedByUserByEstablisment>> getHistory(@Header("Authorization") String token, @Path("mobileNumber") long mobileNumber);

    @PUT(ConstantVariables.UPDATEUSER_URL)
    Call<ResponseBody> updateUser(@Header("Authorization") String token, @Path("mobileNumber") long mobileNumber, @Body UpdateRequest updateRequest);

}
