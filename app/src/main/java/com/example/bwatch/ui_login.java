package com.example.bwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ui_login extends AppCompatActivity {

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private MaterialButton btnLogin;
    private MaterialButton btnSignUp;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);


        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);


        if (etEmail == null || etPassword == null || btnLogin == null || btnSignUp == null) {
            Toast.makeText(this, "Error: UI elements not found", Toast.LENGTH_SHORT).show();
            return;
        }


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });


        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToForgotPassword();
            }
        });
    }


    private void navigateToSignUp() {
        Intent intent = new Intent(ui_login.this, SignUpActivity.class);
        startActivity(intent);
    }


    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();


        etEmail.setError(null);
        etPassword.setError(null);


        if (email.isEmpty()) {
            etEmail.setError("Email or Mobile Number cannot be empty");
            etEmail.requestFocus();
            return;
        }


        if (!isValidEmailOrMobile(email)) {
            etEmail.setError("Please enter a valid email or mobile number");
            etEmail.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
            return;
        }


        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return;
        }


        Toast.makeText(ui_login.this, "Login successful!", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(ui_login.this, HomeActivity.class);
        startActivity(intent);


        finish();
    }


    private void navigateToForgotPassword() {
        Intent intent = new Intent(ui_login.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }


    private boolean isValidEmailOrMobile(String input) {

        if (input.contains("@") && input.contains(".")) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches();
        }


        if (input.matches("^[0-9]{10,11}$")) {
            return true;
        }

        return false;
    }
}