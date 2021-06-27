package com.example.subaybay.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subaybay.AddressActivity;
import com.example.subaybay.AddressAdapter;
import com.example.subaybay.HistoryAdapter;
import com.example.subaybay.LogIn;
import com.example.subaybay.R;
import com.example.subaybay.databinding.FragmentHistoryBinding;
import com.example.subaybay.dto.RefreshTokenRequest;
import com.example.subaybay.dto.ScannedByUserByEstablisment;
import com.example.subaybay.utils.RefreshTokenNow;
import com.example.subaybay.utils.SessionManager;
import com.example.subaybay.utils.SubaybayAPIs;
import com.example.subaybay.utils.SubaybayAuthService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;
    private FragmentHistoryBinding binding;
    private RecyclerView historyRecyclerview;
    private HistoryAdapter adapter;
    private SessionManager sessionManager;
    private SubaybayAuthService service;

    public HistoryFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        historyRecyclerview = root.findViewById(R.id.history_recyclerview);
        service = SubaybayAPIs.subaybayAuthService();
        sessionManager = new SessionManager(getContext());

        Call<List<ScannedByUserByEstablisment>> call = service.getHistory("Bearer "+sessionManager.token(), Long.parseLong(sessionManager.mobileNumber()));
        call.enqueue(new Callback<List<ScannedByUserByEstablisment>>() {
            @Override
            public void onResponse(Call<List<ScannedByUserByEstablisment>> call, Response<List<ScannedByUserByEstablisment>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "History", Toast.LENGTH_SHORT).show();
                    adapter = new HistoryAdapter(getContext());
                    adapter.setHistories(response.body());

                    historyRecyclerview.setAdapter(adapter);
                    historyRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),1));
                }
                else{
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Session expired, you are required to logout", Toast.LENGTH_SHORT).show();
                    RefreshTokenNow loginAgain = new RefreshTokenNow(getContext(), service, sessionManager);
                    loginAgain.logout(new RefreshTokenRequest(sessionManager.token(), sessionManager.mobileNumber()));
                    Intent intent = new Intent(getContext(), LogIn.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<ScannedByUserByEstablisment>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}