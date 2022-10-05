package com.example.orderdish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText edtUsername ;
    TextInputEditText edtPass;
    CheckBox cbRemember;
    SharedPreferences shPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        shPre = getSharedPreferences("dataLogin",MODE_PRIVATE);
         //lấy giá trị
        edtUsername.setText(shPre.getString("taiKhoan",""));
        edtPass.setText(shPre.getString("matKhau",""));
        cbRemember.setChecked(shPre.getBoolean("checked",false));
        BtnLogin();

    }

    public void Anhxa(){
        login = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.username);
        edtPass = findViewById(R.id.pass);
        cbRemember = findViewById(R.id.checkRemember);
    }
    public void BtnLogin() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUsername.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                if (userName.equals("thang") && pass.equals("thang")){
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }





    }