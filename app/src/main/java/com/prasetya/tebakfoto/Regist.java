package com.prasetya.tebakfoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Regist extends AppCompatActivity {
    EditText username, email, password;
    Button btn_reg;
    TextView toLog;
    FirebaseAuth firAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        username = findViewById(R.id.rg_username);
        email = findViewById(R.id.rg_email);
        password =findViewById(R.id.rg_pass);
        btn_reg = findViewById(R.id.rg_btnReg);
        toLog = findViewById(R.id.tx_toLog);

        firAuth = FirebaseAuth.getInstance();

        if (firAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usnam= username.getText().toString().trim();
                String eml = email.getText().toString().trim();

                if (TextUtils.isEmpty(usnam)){
                    username.setError("Username Tidak boleh Kosong!");
                    return;
                }
                if (TextUtils.isEmpty(eml)){
                    email.setError("Email Wajib di Isi!");
                    return;
                }
                firAuth.createUserWithEmailAndPassword(usnam,eml).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Regist.this,"Sukses!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));

                        }else {
                            startActivity(new Intent(getApplicationContext(),Login.class));

                        }
                    }
                });
            }

        });
    }
    public void Tolog(View view) {
        Intent intent = new Intent(Regist.this, Login.class);
        startActivity(intent);

    }

}