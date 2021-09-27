package com.teamcreators.mirrorboard.activitiesmutual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.teamcreators.mirrorboard.R;

/**
 *
 */
public class CreateAccountActivity extends AppCompatActivity {

    private RadioButton selectedMode;
    private EditText phoneNumber, inputPassword, reenteredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        phoneNumber = findViewById(R.id.createAccount_phoneNum);
        inputPassword = findViewById(R.id.createAccount_password);
        reenteredPassword = findViewById(R.id.createAccount_reenteredPassword);

        // selecting app mode, radioGroup button
        RadioGroup appMode = findViewById(R.id.createAccount_radioGroup);
        appMode.setOnCheckedChangeListener((radioGroup, i) -> {
            selectedMode = findViewById(i);
        });

        // back button, back to Login page
        findViewById(R.id.createAccount_back_button).setOnClickListener(view -> {
            // After backing, save the information filled in the Login interface
            onBackPressed();
            finish();
        });

        // next button, go to CreateProfile interface
        findViewById(R.id.createAccount_next_button).setOnClickListener(view -> {
            String phone = phoneNumber.getText().toString().trim();
            String PW = inputPassword.getText().toString().trim();
            String RPW = reenteredPassword.getText().toString().trim();
            if (selectedMode == null) {
                Toast.makeText(CreateAccountActivity.this, "Please choose Elderly or Family", Toast.LENGTH_SHORT).show();
            } else if (phone.isEmpty()) {
                Toast.makeText(CreateAccountActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            } else if (PW.isEmpty()) {
                Toast.makeText(CreateAccountActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            } else if (RPW.isEmpty()) {
                Toast.makeText(CreateAccountActivity.this, "Please confirm your password", Toast.LENGTH_SHORT).show();
            } else if (!PW.equals(RPW)) {
                Toast.makeText(CreateAccountActivity.this, "Passwords must be same", Toast.LENGTH_SHORT).show();
            } else {
                // 跳转到 create profile 界面， 需要传递参数
                String mode = selectedMode.getText().toString().trim();
                Intent intent = new Intent(CreateAccountActivity.this, CreateProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", mode);
                bundle.putString("phone", phone);
                bundle.putString("password", PW);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}