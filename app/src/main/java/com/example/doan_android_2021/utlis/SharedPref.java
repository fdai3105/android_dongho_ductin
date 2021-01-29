package com.example.doan_android_2021.utlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.doan_android_2021.models.User;
import com.google.gson.Gson;

public class SharedPref {
    private final SharedPreferences.Editor editor;
    private final SharedPreferences sharedPreferences;

    public SharedPref(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void saveUserToPref(User user) {
        if (user == null) return;
        Gson s = new Gson();
        editor.putString("user", s.toJson(user));
        editor.apply();
    }

    public User getUserFromPref() {
        Gson g = new Gson();
        return g.fromJson(sharedPreferences.getString("user", ""), User.class);
    }

    public String getToken() {
        if (getUserFromPref() == null) return null;
        return getUserFromPref().getAccessToken();

    }

    public void clear() {
        editor.clear();
        editor.commit();
    }
}
