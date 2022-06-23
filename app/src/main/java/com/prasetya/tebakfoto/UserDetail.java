package com.prasetya.tebakfoto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class UserDetail extends AppCompatActivity {

    private EditText medtNama;
    private EditText medtEmail;

    private Button mDeleteBtn;
    private Button mUpdateBtn;
    private Button mBackBtn;

    private String key;
    private String nama;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        key = getIntent().getStringExtra("username");
        nama = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        medtNama = (EditText) findViewById(R.id.edt_Nama);
        medtNama.setText(nama);
        medtEmail = (EditText) findViewById(R.id.edt_Email);
        medtEmail.setText(email);

        mDeleteBtn = (Button)findViewById(R.id.btn_delete);
        mUpdateBtn = (Button)findViewById(R.id.btn_update);
        mBackBtn = (Button)findViewById(R.id.btn_back);

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setName(medtNama.getText().toString());
                user.setEmail(medtEmail.getText().toString());

                new FbaseHelper().updateUser(key, user, new FbaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<User> users, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(UserDetail.this, "Update Sukses!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FbaseHelper().deleteUser(key, new FbaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<User> users, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(UserDetail.this, "Data Dihapus!", Toast.LENGTH_SHORT).show();
                        finish(); return;

                    }
                });
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();return;
            }
        });
    }
}