package com.example.doan_android_2021.screens.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doan_android_2021.MainActivity;
import com.example.doan_android_2021.R;
import com.example.doan_android_2021.models.User;
import com.example.doan_android_2021.screens.register.RegisterActivity;
import com.example.doan_android_2021.utlis.SharedPref;

public class LoginActivity extends AppCompatActivity implements LoginContact.LoginView {
    LoginPresent loginPresent;

    private EditText email;
    private EditText password;
    private Button submit;
    private Button register;

    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresent = new LoginPresent(this);
        loginPresent.init();

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        submit = findViewById(R.id.login_submit);
        register = findViewById(R.id.login_register);

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.dialog_loading);
        loadingDialog.setCancelable(false);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginPresent.validation(email, password)) {
                    loginPresent.login(email.getText().toString(), password.getText().toString());
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onValidationFail() {
    }

    @Override
    public void onLoginSuccess(User user) {
        SharedPref sharedPref = new SharedPref(getApplicationContext());
        sharedPref.saveUserToPref(user);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFail() {
        Toast.makeText(getApplicationContext(), "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        loadingDialog.show();
    }

    @Override
    public void hideProgress() {
        loadingDialog.dismiss();
    }
}