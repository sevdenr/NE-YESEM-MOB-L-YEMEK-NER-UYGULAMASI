package com.example.tasarimcalismasi.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tasarimcalismasi.R;
import com.example.tasarimcalismasi.adapter.KullaniciAdapter;
import com.example.tasarimcalismasi.adapter.TarifAdapter;
import com.example.tasarimcalismasi.databinding.ActivityKullaniciBinding;
import com.example.tasarimcalismasi.databinding.ActivityTurBinding;
import com.example.tasarimcalismasi.model.Kullanici;
import com.example.tasarimcalismasi.model.Tarif;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class KullaniciActivity extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private  ActivityKullaniciBinding binding;
    private FirebaseAuth auth;

    KullaniciAdapter kullaniciAdapter;
    ArrayList<Kullanici> kullaniciArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKullaniciBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth=FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerViewkullanici = findViewById(R.id.recyclerViewkullanici);
        int spanCount=1;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerViewkullanici.setLayoutManager( layoutManager);

        kullaniciArrayList = new ArrayList<>();
        kullaniciAdapter = new KullaniciAdapter(kullaniciArrayList);
        recyclerViewkullanici.setAdapter(kullaniciAdapter);

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CikisYap();
            }
        });
        getData();
    }
    private void getData() {

        firebaseFirestore.collection("users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(KullaniciActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if (value != null) {
                            kullaniciArrayList.clear();
                            for (DocumentSnapshot snapshot : value.getDocuments()) {
                                Log.d("Firestore", "Snapshot Data: " + snapshot.getData());
                                Map<String, Object> data = snapshot.getData();
                                if (data != null) {
                                    Log.d("Firestore", "Data Map: " + data.toString());
                                    // Sadece TarifAdı ve YemekFoto alanlarını al
                                    String kullaniciAdi = (String) data.get("name");
                                    String KullaniciSoyadi = (String) data.get("surname");
                                    String Email = (String) data.get("email");

                                    Kullanici kullanici = new Kullanici(kullaniciAdi, KullaniciSoyadi, Email);
                                    kullaniciArrayList.add(kullanici);
                                }
                            }
                            kullaniciAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    public void CikisYap(){
        auth.signOut();
        Intent intent1 = new Intent(KullaniciActivity.this,MainActivity.class);
        startActivity(intent1);
        finish();

    }
}