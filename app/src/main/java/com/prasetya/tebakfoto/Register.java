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


public class Register extends AppCompatActivity {

    //Create object to Realtime Database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tebakfoto-default-rtdb.asia-southeast1.firebasedatabase.app/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText yourname = findViewById(R.id.rg_Name);
        final EditText username = findViewById(R.id.rg_Usename);
        final EditText email = findViewById(R.id.rg_Email);
        final EditText pass = findViewById(R.id.rg_Pass);
        final EditText conpass = findViewById(R.id.rg_ConPass);

        final Button regBtn = findViewById(R.id.rg_btnReg);
        final TextView btntoLog = findViewById(R.id.btnToLog);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data form Editexts
                final String yournameTxt = yourname.getText().toString();
                final String usernameTxt = username.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passTxt = pass.getText().toString();
                final String conpassTxt = conpass.getText().toString();

                //Check if user
                if (yournameTxt.isEmpty() || usernameTxt.isEmpty() || emailTxt.isEmpty() || passTxt.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!passTxt.equals(conpassTxt)) {
                    Toast.makeText(Register.this, "Password not matching", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("UserId").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if user is not registered
                            if (snapshot.hasChild(usernameTxt)) {
                                Toast.makeText(Register.this, "Username is Registered", Toast.LENGTH_SHORT).show();
                            } else {

                                //send data to Realtime Database
                                databaseReference.child("UserId").child(usernameTxt).child("name").setValue(yournameTxt);
                                databaseReference.child("UserId").child(usernameTxt).child("email").setValue(emailTxt);
                                databaseReference.child("UserId").child(usernameTxt).child("password").setValue(passTxt);

                                Toast.makeText(Register.this, "Register Sukses!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,Login.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        btntoLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

    }
}
