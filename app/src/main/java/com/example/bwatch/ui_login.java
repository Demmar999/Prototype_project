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

        // Initialize UI elements by ID from the XML
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // Check if all views are properly initialized
        if (etEmail == null || etPassword == null || btnLogin == null || btnSignUp == null) {
            Toast.makeText(this, "Error: UI elements not found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sign Up Button - Navigate to SignUpActivity
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });

        // Login Button - Validate and Navigate to Home
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Forgot Password - Navigate to ForgotPasswordActivity
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToForgotPassword();
            }
        });
    }

    /**
     * Navigate to SignUpActivity when Sign Up button is clicked
     */
    private void navigateToSignUp() {
        Intent intent = new Intent(ui_login.this, SignUpActivity.class);
        startActivity(intent);
    }

    /**
     * Validate email and password, then navigate to Home Activity
     */
    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Clear previous errors
        etEmail.setError(null);
        etPassword.setError(null);

        // Validate email/mobile field is not empty
        if (email.isEmpty()) {
            etEmail.setError("Email or Mobile Number cannot be empty");
            etEmail.requestFocus();
            return;
        }

        // Check if it's a valid email or mobile number
        if (!isValidEmailOrMobile(email)) {
            etEmail.setError("Please enter a valid email or mobile number");
            etEmail.requestFocus();
            return;
        }

        // Validate password field is not empty
        if (password.isEmpty()) {
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
            return;
        }

        // Check password length (minimum 6 characters)
        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return;
        }

        // If all validations pass, show success message
        Toast.makeText(ui_login.this, "Login successful!", Toast.LENGTH_SHORT).show();

        // Navigate to HomeActivity
        Intent intent = new Intent(ui_login.this, HomeActivity.class);
        startActivity(intent);

        // Close the login activity so user can't go back
        finish();
    }

    /**
     * Navigate to ForgotPasswordActivity
     */
    private void navigateToForgotPassword() {
        Intent intent = new Intent(ui_login.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    /**
     * Validate if the input is a valid email or mobile number
     * Email format: example@email.com
     * Mobile format: 10-11 digits
     */
    private boolean isValidEmailOrMobile(String input) {
        // Check if it's an email
        if (input.contains("@") && input.contains(".")) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches();
        }

        // Check if it's a mobile number (10-11 digits)
        if (input.matches("^[0-9]{10,11}$")) {
            return true;
        }

        return false;
    }
}