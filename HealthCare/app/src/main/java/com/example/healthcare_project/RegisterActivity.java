package com.example.healthcare_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUserName, edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUserName = findViewById(R.id.editTextLTBFullName);
        edEmail = findViewById(R.id.editTextLTBAddress);
        edPassword = findViewById(R.id.editTextLTBPincode);
        edConfirm = findViewById(R.id.editTextLTBContact);
        btn = findViewById(R.id.buttonLTBBooking);
        tv = findViewById(R.id.textViewExistingUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserName = edUserName.getText().toString();
                String Email  = edEmail.getText().toString();
                String Password = edPassword.getText().toString();
                String Confirm = edConfirm.getText().toString();

                Database db = new Database(getApplicationContext(),"HealthCare",null,1);

                if( UserName.length()==0 || Email.length()==0 || Password.length()==0 || Confirm.length()==0 ){
                    Toast.makeText(getApplicationContext(),  " Please Fill All Details Carefully ", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(Password.compareTo(Confirm)==0){

                        if( isPasswordValid(Password) ){

                            db.register(UserName,Email,Password);

                            Toast.makeText(getApplicationContext(),  " Registration Successful ", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(),  " Use at least 8 characters one digit and one uppercase in your Password ", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(),  " Password and Confirm Password didn't match ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
            }
        });

    }

    public boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    public boolean isPasswordValid(String password) {

        if (password.length() < 8) {
            return false;
        }

        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
                break;
            }
        }
        if (!hasDigit) {
            return false;
        }

        boolean hasUppercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
                break;
            }
        }
        if (!hasUppercase) {
            return false;
        }

        return true;
    }

    public boolean isPasswordValid2(String password) {

        if (password.length() < 8) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        boolean hasUppercase = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
        }

        return hasDigit && hasSpecialChar && hasUppercase;
    }

}