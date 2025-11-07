package com.example.bwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    // UI Elements
    private TextInputEditText etFirstName;
    private TextInputEditText etLastName;
    private TextInputEditText etEmail;
    private TextInputEditText etPhone;
    private TextInputEditText etPassword;           // ← ADD THIS LINE HERE
    private TextInputEditText etConfirmPassword;    // ← ADD THIS LINE HERE
    private TextInputEditText etAddress;
    private TextInputEditText etCity1;
    private TextInputEditText etCity2;
    private MaterialButton btnSignUp;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        initializeViews();

        // Set click listener
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });
    }

    private void initializeViews() {
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);              // ← ADD THIS LINE HERE
        etConfirmPassword = findViewById(R.id.etConfirmPassword); // ← ADD THIS LINE HERE
        etAddress = findViewById(R.id.etAddress);
        etCity1 = findViewById(R.id.etCity1);
        etCity2 = findViewById(R.id.etCity2);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    private void signUpUser() {
        // Get input values
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();              // ← ADD THIS LINE HERE
        String confirmPassword = etConfirmPassword.getText().toString().trim(); // ← ADD THIS LINE HERE
        String address = etAddress.getText().toString().trim();
        String city1 = etCity1.getText().toString().trim();
        String city2 = etCity2.getText().toString().trim();

        // Clear previous errors
        clearErrors();

        // Validate inputs
        if (!validateInputs(firstName, lastName, email, phone, password, confirmPassword,
                address, city1, city2)) { // ← UPDATE THIS LINE
            return;
        }

        // Disable button to prevent multiple clicks
        btnSignUp.setEnabled(false);
        btnSignUp.setText("Creating account...");

        // Create user with Firebase Authentication - NOW USING REAL PASSWORD
        mAuth.createUserWithEmailAndPassword(email, password) // ← CHANGED FROM tempPassword
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up successful
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                // Save user data to Firestore
                                saveUserToDatabase(user.getUid(), firstName, lastName,
                                        email, phone, address, city1, city2);
                            }
                        } else {
                            // Sign up failed
                            btnSignUp.setEnabled(true);
                            btnSignUp.setText("Sign Up");

                            String errorMessage = "Registration failed.";
                            if (task.getException() != null) {
                                errorMessage = task.getException().getMessage();
                            }
                            Toast.makeText(SignUpActivity.this, errorMessage,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean validateInputs(String firstName, String lastName, String email,
                                   String phone, String password, String confirmPassword,  // ← UPDATE THIS LINE
                                   String address, String city1, String city2) {

        // Validate First Name
        if (firstName.isEmpty()) {
            etFirstName.setError("First name is required");
            etFirstName.requestFocus();
            return false;
        }

        // Validate Last Name
        if (lastName.isEmpty()) {
            etLastName.setError("Last name is required");
            etLastName.requestFocus();
            return false;
        }

        // Validate Email
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return false;
        }

        // Validate Phone
        if (phone.isEmpty()) {
            etPhone.setError("Phone number is required");
            etPhone.requestFocus();
            return false;
        }

        if (phone.length() < 10) {
            etPhone.setError("Phone number must be at least 10 digits");
            etPhone.requestFocus();
            return false;
        }

        // ← ADD PASSWORD VALIDATION HERE
        // Validate Password
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return false;
        }

        // Validate Confirm Password
        if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError("Please confirm your password");
            etConfirmPassword.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return false;
        }
        // ← END OF PASSWORD VALIDATION

        // Validate Address
        if (address.isEmpty()) {
            etAddress.setError("Address is required");
            etAddress.requestFocus();
            return false;
        }

        // Validate City 1
        if (city1.isEmpty()) {
            etCity1.setError("City is required");
            etCity1.requestFocus();
            return false;
        }

        // Validate City 2
        if (city2.isEmpty()) {
            etCity2.setError("City is required");
            etCity2.requestFocus();
            return false;
        }

        return true;
    }

    private void clearErrors() {
        etFirstName.setError(null);
        etLastName.setError(null);
        etEmail.setError(null);
        etPhone.setError(null);
        etPassword.setError(null);          // ← ADD THIS LINE HERE
        etConfirmPassword.setError(null);   // ← ADD THIS LINE HERE
        etAddress.setError(null);
        etCity1.setError(null);
        etCity2.setError(null);
    }

    private void saveUserToDatabase(String userId, String firstName, String lastName,
                                    String email, String phone, String address,
                                    String city1, String city2) {

        // Create user data map
        Map<String, Object> user = new HashMap<>();
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        user.put("phone", phone);
        user.put("address", address);
        user.put("city1", city1);
        user.put("city2", city2);
        user.put("createdAt", System.currentTimeMillis());
        user.put("userId", userId);

        // Save to Firestore
        db.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        btnSignUp.setEnabled(true);
                        btnSignUp.setText("Sign Up");

                        Toast.makeText(SignUpActivity.this,
                                "Account created successfully!",
                                Toast.LENGTH_SHORT).show();

                        // Navigate to Home or Login
                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        btnSignUp.setEnabled(true);
                        btnSignUp.setText("Sign Up");

                        Toast.makeText(SignUpActivity.this,
                                "Failed to save user data: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}