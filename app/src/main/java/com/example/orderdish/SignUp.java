package com.example.orderdish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    Button clear , btnRegister;
    EditText emailSU,userSU,passSU,birthSU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        clear = findViewById(R.id.btnclear);
        btnRegister = findViewById(R.id.btnRegister);
        emailSU = findViewById(R.id.emailSignUp);
        userSU = findViewById(R.id.usernameSignUp);
        passSU = findViewById(R.id.passWordSignUp);
        birthSU = findViewById(R.id.birthSignUp);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailSU.setText("");
                userSU.setText("");
                passSU.setText("");
                birthSU.setText("");

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,SignIn.class));
            }
        });
    }

}