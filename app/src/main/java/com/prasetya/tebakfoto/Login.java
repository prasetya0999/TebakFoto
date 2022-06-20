package com.prasetya.tebakfoto;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    String username = "agung";
    String pass = "agung1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText lg_Usename = findViewById(R.id.lg_Usename);
        EditText lg_Pass = findViewById(R.id.lg_Pass);
        Button log = findViewById(R.id.lg_btnLog);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lg_Usename.getText().toString().equalsIgnoreCase(username)&&lg_Pass.getText().toString().equalsIgnoreCase(pass)){
                    startActivity(new Intent(Login.this, Pilih.class));
                }else {
                    Toast.makeText(Login.this,"Username/Password Salah!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
