package com.example.doan_android_2021.screens.dashboard.user;

import com.example.doan_android_2021.BaseContact;
import com.example.doan_android_2021.models.User;

public interface UserContact {
    interface UserView extends BaseContact.BaseView {
        void authFail();

        void authSuccess(User user);
    }

    interface UserPresent extends BaseContact.BasePresent {
        void getUser();

        boolean isLogin();
    }
}
