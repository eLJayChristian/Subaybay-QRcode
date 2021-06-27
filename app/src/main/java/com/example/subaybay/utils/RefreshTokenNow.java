package com.example.subaybay.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.subaybay.LogIn;
import com.example.subaybay.MainActivity;
import com.example.subaybay.dto.RefreshTokenRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefreshTokenNow {

    private SubaybayAuthService service;
    private Context context;
    private SessionManager sessionManager;

    public RefreshTokenNow(Context context, SubaybayAuthService service, SessionManager sessionManager){
        this.context = context;
        this.service = service;
        this.sessionManager = sessionManager;
    }

    public void logout(RefreshTokenRequest tokenRequest){
        Call<ResponseBody> logout = service.logout(tokenRequest);
        logout.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "logout", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutUser();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
