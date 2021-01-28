package com.example.doan_android_2021.screens.login;

import android.widget.EditText;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.User;

interface LoginContact {
    interface LoginView extends BaseContact.BaseView {
        void onValidationFail();

        void onLoginSuccess(User user);

        void onLoginFail();
    }

    interface LoginPresent extends BaseContact.BasePresent {
        boolean validation(EditText email, EditText password);

        void login(String email, String password);
    }
}
