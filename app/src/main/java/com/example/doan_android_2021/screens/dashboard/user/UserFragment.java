package com.example.doan_android_2021.screens.dashboard.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.doan_android_2021.MainActivity;
import com.example.doan_android_2021.R;
import com.example.doan_android_2021.models.User;
import com.example.doan_android_2021.screens.login.LoginActivity;
import com.example.doan_android_2021.utlis.SharedPref;

public class UserFragment extends Fragment implements UserContact.UserView {
    private UserPresent userPresent;
    private LinearLayout llLogin;
    private LinearLayout llTitle;
    private LinearLayout userLogout;
    private TextView userName;
    private TextView userPhone;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);

        llLogin = root.findViewById(R.id.user_view_login);
        llTitle = root.findViewById(R.id.user_view_title);
        userName = root.findViewById(R.id.user_name);
        userPhone = root.findViewById(R.id.user_phone);
        userLogout = root.findViewById(R.id.user_logout);

        llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        SharedPref pref = new SharedPref(getContext());
        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.clear();
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        userPresent = new UserPresent(this, pref);
        userPresent.init();

        return root;
    }

    @Override
    public void authSuccess(User user) {
        llLogin.setVisibility(View.GONE);
        llTitle.setVisibility(View.VISIBLE);
        userName.setText(user.getUser().getFullName());
        userPhone.setText(user.getUser().getPhoneNumber());

    }

    @Override
    public void authFail() {
        llLogin.setVisibility(View.VISIBLE);
        llTitle.setVisibility(View.GONE);
        userLogout.setVisibility(View.GONE);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}