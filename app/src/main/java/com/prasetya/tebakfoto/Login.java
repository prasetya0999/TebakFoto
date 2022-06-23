package com.prasetya.tebakfoto;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tebakfoto-default-rtdb.asia-southeast1.firebasedatabase.app/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.lg_Usename);
        final EditText pass = findViewById(R.id.lg_Pass);

        final Button logBtn = findViewById(R.id.lg_btnLog);
        final TextView btnToReg = findViewById(R.id.btnToReg);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameTxt = username.getText().toString();
                final String passTxt = pass.getText().toString();

                if (usernameTxt.isEmpty()&&passTxt.isEmpty()){
                    Toast.makeText(Login.this, "Username & Password Tidak boleh Kosong!", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("UserId").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if username is exist in database
                            if (snapshot.hasChild(usernameTxt)){
                                //username is exist in database

                                final String getPassword = snapshot.child(usernameTxt).child("password").getValue(String.class);

                                if (getPassword.equals(passTxt)){
                                    Toast.makeText(Login.this, "Login Success!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this,Pilih.class));
                                    finish();
                                }else {
                                    Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        btnToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Open Register Activity
                startActivity(new Intent(Login.this,Register.class));
            }
        });

    }
}