package com.example.doan_android_2021.screens.register;

import android.util.Log;
import android.util.Patterns;

import com.example.doan_android_2021.data.remote.ApiClient;
import com.example.doan_android_2021.data.remote.services.UserService;
import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RegisterPresent implements RegisterContact.RegisterPresent {
    private final RegisterContact.RegisterView view;
    private static UserService userService;

    RegisterPresent(RegisterContact.RegisterView view) {
        this.view = view;
        userService = ApiClient.getUserService();
    }

    @Override
    public boolean validation(String name, String email, String pass, String confirmPass, String phone) {
        if (name == null || email == null || pass == null || confirmPass == null || phone == null) {
            if (name == null) {
                view.onNameValFail("Tên người dùng không được để trống");
                return false;
            }
            if (email == null) {
                view.onEmailValFail("Email không được để trống");
                return false;
            }
            if (pass == null) {
                view.onPassValFail("Email không được để trống");
                return false;
            }
            if (confirmPass == null) {
                view.onConfirmPassValFail("Nhập lại mật khẩu không được để trống");
                return false;
            }
            if (phone == null) {
                view.onPhoneValFail("Số điện thoại không được để trống");
                return false;
            }
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.onEmailValFail("Email không đúng định dạng");
            return false;
        }
        if (!Patterns.PHONE.matcher(phone).matches()) {
            view.onPhoneValFail("Số điện thoại không đúng định dạng");
            return false;
        }

        if (!pass.equals(confirmPass)) {
            view.onConfirmPassValFail("Mật khẩu không trung");
            return false;
        }

        return true;
    }

    @Override
    public void register(String name, String email, String pass, String confirmPass, String phone, int gender) {
        HashMap<String, String> body = new HashMap<>();
        body.put("full_name", name);
        body.put("email", email);
        body.put("password", pass);
        body.put("phone_number", phone);
        body.put("gender", String.valueOf(gender));

        userService.register(body).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.code() == 200) {
                    view.onRegisterSuccess();
                } else if (response.code() == 422) {
                    view.onEmailValFail("Email đã có người sử dụng");
                } else {
                    view.onRegisterFail("Đăng ký thất bại");
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
                view.onRegisterFail("Đăng ký thất bại");
            }
        });
    }
}
