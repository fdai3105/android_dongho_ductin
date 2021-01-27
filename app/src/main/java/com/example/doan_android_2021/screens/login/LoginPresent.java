package com.example.doan_android_2021.screens.login;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.services.UserService;
import com.example.doan_android_2021.models.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginPresent implements LoginContact.LoginPresent {
    private LoginContact.LoginView homeView;
    private static UserService userService;

    public LoginPresent(LoginContact.LoginView homeView) {
        this.homeView = homeView;
        this.userService = ApiClient.getUserService();
    }

    void init() {
    }


    @Override
    public boolean loginValidation(EditText email, EditText password) {
        String emailVal = email.getText().toString().trim();
        String passwordVal = password.getText().toString().trim();
        if (emailVal.length() < 1 || passwordVal.length() < 1) {
            if (emailVal.length() < 1) {
                email.setError("Email đăng nhập không được để trống");
                email.requestFocus();
                return false;
            } else if (passwordVal.length() < 1) {
                password.setError("Mật khẩu không được để trống");
                password.requestFocus();
                return false;
            } else
                return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Email không đúng định dạng");
            email.requestFocus();
            return false;
        }
        if (password.length() <= 8) {
            password.setError("Mật khẩu phải trên 8 kí tự");
            password.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void login(String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        userService.login(body).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                homeView.showProgress();
                if (response.code() == 200) {
                    homeView.onLoginSuccess(response.body());
                } else {
                    homeView.onLoginFail();
                }
                homeView.hideProgress();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
}
