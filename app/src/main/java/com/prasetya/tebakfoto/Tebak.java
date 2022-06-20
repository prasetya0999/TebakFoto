package com.prasetya.tebakfoto;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Tebak extends AppCompatActivity {
    ImageView imgV_tebak;
    EditText edT_jawab;
    Button bt_cek;
    String jwb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tebak);

        setInitalis();
        cekIntent();
        onClickjos();
    }
    private void onClickjos(){
        bt_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edT_jawab.getText().toString().equals(jwb)){
                    Toast.makeText(Tebak.this,"Anda BENAR!!!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Tebak.this,"Anda SALAH!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void cekIntent(){
        Intent ic=getIntent();String nama_icon = ic.getStringExtra("nama_icon");
        if (nama_icon.equals("luffy")){
            imgV_tebak.setImageResource(R.drawable.luffy);
            jwb ="luffy";
        }else  if (nama_icon.equals("naruto")){
            imgV_tebak.setImageResource(R.drawable.naruto);
            jwb ="naruto";
        }else if (nama_icon.equals("saitama")) {
            imgV_tebak.setImageResource(R.drawable.saitama);
            jwb = "saitama";
        }else if (nama_icon.equals("marin")) {
            imgV_tebak.setImageResource(R.drawable.marin);
            jwb = "marim";
        }else if (nama_icon.equals("asuna")) {
            imgV_tebak.setImageResource(R.drawable.asuna);
            jwb = "asuna";
        }else if (nama_icon.equals("nezuko")) {
            imgV_tebak.setImageResource(R.drawable.nezuko);
            jwb = "nezuko";
        }
    }
    private void setInitalis(){
        imgV_tebak= findViewById(R.id.imageView_tebak);
        edT_jawab= findViewById(R.id.editText_jawab);
        bt_cek= findViewById(R.id.buttoncek);
    }
}
