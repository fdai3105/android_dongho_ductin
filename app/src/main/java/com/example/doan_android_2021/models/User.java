package com.example.doan_android_2021.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user")
    @Expose
    private UserDatum user;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("expires_at")
    @Expose
    private String expiresAt;

    public UserDatum getUser() {
        return user;
    }

    public void setUser(UserDatum user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "user=" + user +
                ", accessToken='" + accessToken + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                '}';
    }
}

