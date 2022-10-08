package com.example.orderdish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignIn extends AppCompatActivity {
    Button login, btnSignUp;
    EditText edtUsername ;
    TextInputEditText edtPass;
    CheckBox cbRemember;
    SharedPreferences shPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Anhxa();
        shPre = getSharedPreferences("dataLogin",MODE_PRIVATE);
         //lấy giá trị
        edtUsername.setText(shPre.getString("taiKhoan",""));
        edtPass.setText(shPre.getString("matKhau",""));
        cbRemember.setChecked(shPre.getBoolean("checked",false));
        BtnLogin();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this,SignUp.class));
            }
        });


    }

    public void Anhxa(){
        login = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.username);
        edtPass = findViewById(R.id.pass);
        cbRemember = findViewById(R.id.checkRemember);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
    public void BtnLogin() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUsername.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();


                        if (userName.equals("thang") && pass.equals("thang")){
                            Toast.makeText(SignIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                                   startActivity(new Intent(SignIn.this, Home.class));

                            if (cbRemember.isChecked()){
                                SharedPreferences.Editor editor = shPre.edit();
                                editor.putString("taiKhoan",userName);
                                editor.putString("matKhau",pass);
                                editor.putBoolean("checked",true);
                                editor.commit();
                            }else{
                                SharedPreferences.Editor editor = shPre.edit();
                                editor.remove("taiKhoan");
                                editor.remove("matKhau");
                                editor.remove("checked");
                                editor.commit();
                            }



                        }else {
                            Toast.makeText(SignIn.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
            }
        });


    }





    }