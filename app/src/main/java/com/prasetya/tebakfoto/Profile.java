package com.prasetya.tebakfoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.List;


public class Profile extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        recyclerView=(RecyclerView) findViewById(R.id.prof_list);

        final Button btn_Back = findViewById(R.id.profBtn_back);

        new FbaseHelper().readUser(new FbaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<User> users, List<String> keys) {
                new RcConFig().setConfig(recyclerView,Profile.this,users,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
        btn_Back.setOnClickListener(view -> startActivity(new Intent(Profile.this,Pilih.class)));

    }
}