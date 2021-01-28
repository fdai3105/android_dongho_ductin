package com.example.doan_android_2021.screens.dashboard.user;

import android.util.Log;

import com.example.doan_android_2021.utlis.SharedPref;

class UserPresent implements UserContact.UserPresent {
    private final UserContact.UserView view;
    private final SharedPref pref;

    public UserPresent(UserContact.UserView view, SharedPref pref) {
        this.view = view;
        this.pref = pref;
    }

    void init() {
        getUser();
    }

    @Override
    public void getUser() {
        if (isLogin()) {
            view.authSuccess(pref.getUserFromPref());
        } else {
            view.authFail();
        }
    }

    @Override
    public boolean isLogin() {
        return pref.getUserFromPref() != null;
    }
}
