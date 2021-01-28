package com.example.doan_android_2021.screens.register;

import com.example.doan_android_2021.BaseContact;

public interface RegisterContact {
    interface RegisterView extends BaseContact.BaseView {
        void onNameValFail(String message);

        void onEmailValFail(String message);

        void onPassValFail(String message);

        void onConfirmPassValFail(String message);

        void onPhoneValFail(String message);

        void onRegisterSuccess();

        void onRegisterFail(String message);
    }

    interface RegisterPresent extends BaseContact.BasePresent {
        boolean validation(String name, String email, String pass, String confirmPass, String phone);

        void register(String name, String email, String pass, String confirmPass, String phone,int gender);
    }
}
