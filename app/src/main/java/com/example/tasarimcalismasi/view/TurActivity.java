package com.example.tasarimcalismasi.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasarimcalismasi.R;
import com.example.tasarimcalismasi.adapter.OnItemClickListener;
import com.example.tasarimcalismasi.adapter.TarifAdapter;
import com.example.tasarimcalismasi.databinding.ActivityTurBinding;
import com.example.tasarimcalismasi.model.Tarif;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class TurActivity extends AppCompatActivity implements OnItemClickListener {
    private FirebaseFirestore firebaseFirestore;
    private ActivityTurBinding binding;
    TarifAdapter tarifAdapter;
    ArrayList<Tarif> tarifArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTurBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Intent intent2 = getIntent();
        String turAdi = getIntent().getStringExtra("TürAdı");
        TextView textView = binding.textTurAdi;
        textView.setText(turAdi);



        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerViewAnaSayfa = findViewById(R.id.recyclerViewAnaSayfa);
        int spanCount=1;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerViewAnaSayfa.setLayoutManager( layoutManager);

        tarifArrayList = new ArrayList<>();
        tarifAdapter = new TarifAdapter(tarifArrayList, this);
        recyclerViewAnaSayfa.setAdapter(tarifAdapter);

        binding.homeTur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home();
            }
        });
        binding.questionTur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question();
            }
        });
        binding.usersTur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users();
            }
        });

        getData(turAdi);


        

    }
    private void getData(String turAdi) {
        Log.d("TurAdi", "Tur Adi: " + turAdi);
        firebaseFirestore.collection("Tarif").whereEqualTo("Tür", turAdi)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(TurActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if (value != null) {
                            tarifArrayList.clear();
                            for (DocumentSnapshot snapshot : value.getDocuments()) {
                                Log.d("Firestore", "Snapshot Data: " + snapshot.getData());
                                Map<String, Object> data = snapshot.getData();
                                if (data != null) {
                                    Log.d("Firestore", "Data Map: " + data.toString());
                                    // Sadece TarifAdı ve YemekFoto alanlarını al
                                    String tarifAdi = (String) data.get("TarifAdı");
                                    String yemekFoto = (String) data.get("YemekFoto");

                                    Tarif tarif = new Tarif(tarifAdi, yemekFoto);
                                    tarifArrayList.add(tarif);
                                }
                            }
                            tarifAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


    public void TextViewFonk(View view){
        Intent intent = new Intent(TurActivity.this, AnaSayfaActivity.class);
        startActivity(intent);

    }
    public void Home(){
        //auth.signOut();
        Intent intent1 = new Intent(TurActivity.this,AnaSayfaActivity.class);
        startActivity(intent1);

    }
    public void Question(){
        Intent intent1 = new Intent(TurActivity.this,SorguActivity.class);
        startActivity(intent1);
    }
    public void Users(){
        Intent intent1 = new Intent(TurActivity.this,KullaniciActivity.class);
        startActivity(intent1);
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(TurActivity.this, TarifActivity.class);
        intent.putExtra("TarifAdı",tarifArrayList.get(position).getTarifAdi());
        startActivity(intent);

    }
}