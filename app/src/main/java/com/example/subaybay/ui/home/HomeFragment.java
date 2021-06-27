package com.example.subaybay.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.subaybay.R;
import com.example.subaybay.databinding.FragmentHomeBinding;
import com.example.subaybay.dto.RefreshTokenRequest;
import com.example.subaybay.model.User;
import com.example.subaybay.utils.RefreshTokenNow;
import com.example.subaybay.utils.SessionManager;
import com.example.subaybay.utils.SubaybayAPIs;
import com.example.subaybay.utils.SubaybayAuthService;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{
    private SessionManager sessionManager;
    private SubaybayAuthService service;
    private ImageView qrView;
    private Button generateBtn;

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public HomeFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sessionManager = new SessionManager(getContext());

        qrView = root.findViewById(R.id.Qr);
        generateBtn = root.findViewById(R.id.GenerateBtn);

        service = SubaybayAPIs.subaybayAuthService();
        Call<User> call = service.getUserProfile("Bearer "+sessionManager.token(), Long.parseLong(sessionManager.mobileNumber()));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    long userid =  response.body().getId();
                    QRGEncoder qrgEncoder = new QRGEncoder(String.valueOf(userid),null, QRGContents.Type.TEXT,500);
                    Bitmap qrBits = qrgEncoder.getBitmap();
                    qrView.setImageBitmap(qrBits);
                }
                else{
                    System.out.println(response.code());
                    Toast.makeText(getContext(), "Session expired, you are required to logout", Toast.LENGTH_SHORT).show();
                    RefreshTokenNow loginAgain = new RefreshTokenNow(getContext(), service, sessionManager);
                    loginAgain.logout(new RefreshTokenRequest(sessionManager.token(), sessionManager.mobileNumber()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        generateBtn.setOnClickListener(v -> {

            service = SubaybayAPIs.subaybayAuthService();
            Call<User> call1 = service.getUserProfile("Bearer "+sessionManager.token(), Long.parseLong(sessionManager.mobileNumber()));
            call1 .enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                       long userid =  response.body().getId();
                        QRGEncoder qrgEncoder = new QRGEncoder(String.valueOf(userid),null, QRGContents.Type.TEXT,500);
                        Bitmap qrBits = qrgEncoder.getBitmap();
                        qrView.setImageBitmap(qrBits);
                    }
                    else{
                        Toast.makeText(getContext(), "Session expired, you are required to logout", Toast.LENGTH_SHORT).show();
                        RefreshTokenNow loginAgain = new RefreshTokenNow(getContext(), service, sessionManager);
                        loginAgain.logout(new RefreshTokenRequest(sessionManager.token(), sessionManager.mobileNumber()));
                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}