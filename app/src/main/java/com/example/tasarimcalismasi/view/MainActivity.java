package com.example.tasarimcalismasi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tasarimcalismasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(MainActivity.this, AnaSayfaActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void girisYap(View view){
        Intent intent =new Intent(MainActivity.this,GirisActivity.class);
        startActivities(new Intent[]{intent});
        finish();
    }
}