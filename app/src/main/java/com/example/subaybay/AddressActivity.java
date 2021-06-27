package com.example.subaybay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.subaybay.dto.RefreshTokenRequest;
import com.example.subaybay.model.Address;
import com.example.subaybay.utils.RefreshTokenNow;
import com.example.subaybay.utils.SessionManager;
import com.example.subaybay.utils.SubaybayAPIs;
import com.example.subaybay.utils.SubaybayAuthService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AddressAdapter adapter;
    private SubaybayAuthService service;
    private SessionManager sessionManager;
    private Intent extra;
    private int errorCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        this.extra = getIntent();

        recyclerView = findViewById(R.id.address_list_recyclerview);
        service = SubaybayAPIs.subaybayAuthService();
        sessionManager = new SessionManager(this);

        Call<List<Address>> call = service.getAllUserAddress("Bearer "+sessionManager.token(), extra.getExtras().getLong("userid"));
        call.enqueue(new Callback<List<Address>>() {
            @Override
            public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                if(response.isSuccessful()){

                    adapter = new AddressAdapter(AddressActivity.this);
                    adapter.setListOfAddress(response.body());

                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(AddressActivity.this,1));
                    Toast.makeText(AddressActivity.this, "success naman sya", Toast.LENGTH_SHORT).show();

                }
                else if (response.code() == 403 || response.code() == 401){
                    Toast.makeText(AddressActivity.this, "Session expired, you are required to logout", Toast.LENGTH_SHORT).show();
                    errorCode = response.code();
                    RefreshTokenNow loginAgain = new RefreshTokenNow(getApplicationContext(), service, sessionManager);
                    loginAgain.logout(new RefreshTokenRequest(sessionManager.token(), sessionManager.mobileNumber()));
                }
            }

            @Override
            public void onFailure(Call<List<Address>> call, Throwable t) {
                Toast.makeText(AddressActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error:",t.getMessage());
            }
        });






    }

}