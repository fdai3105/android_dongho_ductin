package com.example.doan_android_2021.screens.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.screens.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterContact.RegisterView {
    /**/
    TextView tvName;
    TextView tvEmail;
    TextView tvPass;
    TextView tvConfirmPass;
    TextView tvPhone;
    RadioButton rdbMale, rdbFemale, rdbOther;
    Button btnSubmit;
    Button btnToLogin;

    private Dialog loadingDialog;

    /**/
    RegisterPresent present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /* findView */
        tvName = findViewById(R.id.register_name);
        tvEmail = findViewById(R.id.register_email);
        tvPass = findViewById(R.id.register_password);
        tvConfirmPass = findViewById(R.id.register_confirm_password);
        tvPhone = findViewById(R.id.register_phone);
        rdbMale = findViewById(R.id.register_rdb_male);
        rdbFemale = findViewById(R.id.register_rdb_female);
        rdbOther = findViewById(R.id.register_rdb_other);
        btnSubmit = findViewById(R.id.register_submit);
        btnToLogin = findViewById(R.id.register_to_login);

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.dialog_loading);
        loadingDialog.setCancelable(false);

        /**/
        present = new RegisterPresent(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tvName.getText().toString().trim();
                String email = tvEmail.getText().toString().trim();
                String pass = tvPass.getText().toString().trim();
                String confirmPass = tvConfirmPass.getText().toString().trim();
                String phone = tvPhone.getText().toString().trim();
                int gender = 0;
                if (rdbMale.isChecked()) {
                    gender = 1;
                } else if (rdbFemale.isChecked()) {
                    gender = 2;
                } else if (rdbOther.isChecked()) {
                    gender = 0;
                }
                if (present.validation(name, email, pass, confirmPass, phone)) {
                    present.register(name, email, pass, confirmPass, phone, gender);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        loadingDialog.show();
    }

    @Override
    public void hideProgress() {
        loadingDialog.dismiss();
    }

    @Override
    public void onNameValFail(String message) {
        if (message == null) return;
        tvName.requestFocus();
        tvName.setError(message);
    }

    @Override
    public void onEmailValFail(String message) {
        if (message == null) return;
        tvEmail.requestFocus();
        tvEmail.setError(message);
    }

    @Override
    public void onPassValFail(String message) {
        if (message == null) return;
        tvPass.requestFocus();
        tvPass.setError(message);
    }

    @Override
    public void onConfirmPassValFail(String message) {
        if (message == null) return;
        tvConfirmPass.requestFocus();
        tvConfirmPass.setError(message);
    }

    @Override
    public void onPhoneValFail(String message) {
        if (message == null) return;
        tvPhone.requestFocus();
        tvPhone.setError(message);
    }

    @Override
    public void onRegisterSuccess() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRegisterFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}