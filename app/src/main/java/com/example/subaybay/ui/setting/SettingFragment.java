package com.example.subaybay.ui.setting;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.subaybay.AddressActivity;
import com.example.subaybay.R;

import com.example.subaybay.UpdateProfileActivity;
import com.example.subaybay.databinding.FragmentSettingBinding;
import com.example.subaybay.dto.RefreshTokenRequest;
import com.example.subaybay.dto.UpdateRequest;
import com.example.subaybay.model.User;
import com.example.subaybay.utils.RefreshTokenNow;
import com.example.subaybay.utils.SessionManager;
import com.example.subaybay.utils.SubaybayAPIs;
import com.example.subaybay.utils.SubaybayAuthService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragment extends Fragment {

    private SettingViewModel settingViewModel;
    private FragmentSettingBinding binding;

    private SubaybayAuthService service;
    private SessionManager sessionManager;

    private TextView name, phoneNumber, vaccinated, birthday;
    private Button editBtn, addressBtn;
    private long birthdayResponse;

    private long userid;
    private UpdateRequest userInformation;

    public SettingFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.name = root.findViewById(R.id.Fname);
        this.birthday = root.findViewById(R.id.Bday);
        this.phoneNumber = root.findViewById(R.id.phoneNum);
        this.vaccinated = root.findViewById(R.id.vaccineAuthen);
        this.editBtn = root.findViewById(R.id.editBtn);
        this.addressBtn = root.findViewById(R.id.button4);

        this.editBtn.setEnabled(false);
        this.addressBtn.setEnabled(false);

        // onclicklistener
        this.editBtn.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), UpdateProfileActivity.class);
            intent.putExtra("firstname", userInformation.getFirstname());
            intent.putExtra("middlename", userInformation.getMiddlename());
            intent.putExtra("lastname", userInformation.getLastname());
            intent.putExtra("birthday", userInformation.getBirthday());
            intent.putExtra("isVaccinated", userInformation.isVaccinated());
            intent.putExtra("password", userInformation.getPassword());
            intent.putExtra("isAdmin", userInformation.isAdmin());
            intent.putExtra("mobilenumber", userInformation.getMobileNumber());
            startActivity(intent);

        });
        // onclicklistener
        this.addressBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddressActivity.class);
            intent.putExtra("userid", userid);
            startActivity(intent);
        });


        sessionManager = new SessionManager(getContext());

        service = SubaybayAPIs.subaybayAuthService();

        Call<User> call = service.getUserProfile("Bearer "+sessionManager.token(), Long.parseLong(sessionManager.mobileNumber()));
        call.enqueue(new Callback<User>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){

                    userInformation = new UpdateRequest();
                    // storing data from api call
                    userInformation.setFirstname(response.body().getFirstname());
                    userInformation.setMiddlename(response.body().getMiddlename());
                    userInformation.setLastname(response.body().getLastname());
                    userInformation.setBirthday(response.body().getBirthday());
                    userInformation.setMobileNumber(response.body().getMobileNumber());
                    userInformation.setVaccinated(response.body().isVaccinated());
                    userInformation.setPassword(response.body().getPassword());
                    userInformation.setAdmin(response.body().isAdmin());

                    // iniatializing few information about the user
                    birthdayResponse = Long.parseLong(response.body().getBirthday());
                    long timestamp = birthdayResponse;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateTimeBirthday = sdf.format(timestamp);

                    userid =  response.body().getId(); // tenga nalang muna natin to

                    // Display few information
                    name.setText(response.body().getFirstname()+" "+response.body().getMiddlename()+". "+response.body().getLastname());
                    phoneNumber.setText("+63"+response.body().getMobileNumber());
                    birthday.setText(dateTimeBirthday);
                    vaccinated.setText(response.body().isVaccinated()? "Yes":"No");
                    editBtn.setEnabled(true);
                    addressBtn.setEnabled(true);
                }
                else{
                    System.out.println(response.code());
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}