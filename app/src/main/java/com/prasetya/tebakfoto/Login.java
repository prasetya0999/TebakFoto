package com.prasetya.tebakfoto;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText lg_Usename, lg_Pass;
    Button lg_log;
    String username = "agung";
    String pass = "agung1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lg_Usename = findViewById(R.id.lg_Usename);
        lg_Pass = findViewById(R.id.lg_Pass);
        lg_log = findViewById(R.id.lg_btnLog);

        lg_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lg_Usename.getText().toString().equalsIgnoreCase(username)&&lg_Pass.getText().toString().equalsIgnoreCase(pass)){
                    startActivity(new Intent(Login.this, Tebak.class));
                }else {
                    Toast.makeText(Login.this,"Username/Password Salah!",Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

}
