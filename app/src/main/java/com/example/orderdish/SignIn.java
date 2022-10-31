package com.example.orderdish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://oderfood-7acec-default-rtdb.firebaseio.com");
    Button login, btnSignUp;
    EditText PhoneNumber;
    TextInputEditText edtPass;
    CheckBox cbRemember;
    SharedPreferences shPre;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Anhxa();
        shPre = getSharedPreferences("dataLogin",MODE_PRIVATE);
         //lấy giá trị
        PhoneNumber.setText(shPre.getString("taiKhoan",""));
        edtPass.setText(shPre.getString("matKhau",""));
        cbRemember.setChecked(shPre.getBoolean("checked",false));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this,SignUp.class));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("please waiting ...");
                mDialog.show();

                final String PhoneLogin = PhoneNumber.getText().toString();
                final String pass = edtPass.getText().toString();

                if (PhoneLogin.isEmpty() || pass.isEmpty()) {
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "please your enter username or id or pass", Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(PhoneLogin)){
                                final  String getPassword = snapshot.child(PhoneLogin).child("password").getValue(String.class);
                                if(getPassword.equals(pass)){
                                    mDialog.dismiss();
                                    Toast.makeText(SignIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(SignIn.this, navigation_home.class));
                                    if (cbRemember.isChecked()){
                                        SharedPreferences.Editor editor = shPre.edit();
                                        editor.putString("taiKhoan",PhoneLogin.trim());
                                        editor.putString("matKhau",pass.trim());
                                        editor.putBoolean("checked",true);
                                        editor.commit();
                                    }else{
                                        SharedPreferences.Editor editor = shPre.edit();
                                        editor.remove("taiKhoan");
                                        editor.remove("matKhau");
                                        editor.remove("checked");
                                        editor.commit();
                                    }
                                }else{
                                    mDialog.dismiss();
                                    Toast.makeText(SignIn.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
            });


//                db.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                        //check if user not exit in database
//                        if(snapshot.hasChild(edtUsername.getText().toString())){
//                            User user = snapshot.child(edtUsername.getText().toString()).getValue(User.class);
//
//
//                            Toast.makeText(SignIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                        }
//                            mDialog.dismiss();
                // get user info
//                           User user = snapshot.child(edtUsername.getText().toString()).getValue(User.class);
//                            if(user.getPassword().equals(edtPass.getText().toString())){
//                                Toast.makeText(SignIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//
//                                startActivity(new Intent(SignIn.this, navigation_home.class));
//                                if (cbRemember.isChecked()){
//                                    SharedPreferences.Editor editor = shPre.edit();
//                                    editor.putString("taiKhoan",userName);
//                                    editor.putString("matKhau",pass);
//                                    editor.putBoolean("checked",true);
//                                    editor.commit();
//                                }else{
//                                    SharedPreferences.Editor editor = shPre.edit();
//                                    editor.remove("taiKhoan");
//                                    editor.remove("matKhau");
//                                    editor.remove("checked");
//                                    editor.commit();
//                                }
//                            }
//                            else{
//                                mDialog.dismiss();
//                                Toast.makeText(SignIn.this, "Sign in failed", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else {
//                            Toast.makeText(SignIn.this, "user not exits", Toast.LENGTH_SHORT).show();
//                        }


            }

//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        });


            //  }

            public void Anhxa() {
                login = findViewById(R.id.btnLogin);
                PhoneNumber = findViewById(R.id.PhoneNumber);
                edtPass = findViewById(R.id.pass);
                cbRemember = findViewById(R.id.checkRemember);
                btnSignUp = findViewById(R.id.btnSignUp);
            }

            public void BtnLogin() {

                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String phone = PhoneNumber.getText().toString().trim();
                        String pass = edtPass.getText().toString().trim();


                        if (phone.equals("thang") && pass.equals("thang")) {
                            Toast.makeText(SignIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(SignIn.this, navigation_home.class));

                            if (cbRemember.isChecked()) {
                                SharedPreferences.Editor editor = shPre.edit();
                                editor.putString("taiKhoan", phone);
                                editor.putString("matKhau", pass);
                                editor.putBoolean("checked", true);
                                editor.commit();
                            } else {
                                SharedPreferences.Editor editor = shPre.edit();
                                editor.remove("taiKhoan");
                                editor.remove("matKhau");
                                editor.remove("checked");
                                editor.commit();
                            }


                        } else {
                            Toast.makeText(SignIn.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        }