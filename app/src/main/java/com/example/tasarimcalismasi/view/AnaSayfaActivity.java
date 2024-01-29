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
import android.widget.Toast;

import com.example.tasarimcalismasi.R;
import com.example.tasarimcalismasi.adapter.OnItemClickListener;
import com.example.tasarimcalismasi.adapter.TurAdapter;
import com.example.tasarimcalismasi.databinding.ActivityAnaSayfaBinding;
import com.example.tasarimcalismasi.model.Tur;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class AnaSayfaActivity extends AppCompatActivity implements OnItemClickListener {
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private ActivityAnaSayfaBinding binding;

    ArrayList<Tur> turArrayList;

    TurAdapter turAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnaSayfaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        int spanCount = 2; // İki sütunlu bir düzen
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView2.setLayoutManager(layoutManager);

        turArrayList = new ArrayList<>();
        turAdapter = new TurAdapter(turArrayList, this);
        recyclerView2.setAdapter(turAdapter);

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home();
            }
        });
        binding.question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question();
            }
        });
        binding.users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users();
            }
        });
        getData();

    }

    private  void getData(){
        firebaseFirestore.collection("Tür").addSnapshotListener(new EventListener<com.google.firebase.firestore.QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Toast.makeText(AnaSayfaActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if(value != null){
                    turArrayList.clear();
                    for (DocumentSnapshot snapshot: value.getDocuments()){
                        Log.d("Firestore", "Snapshot Data: " + snapshot.getData());
                        Map<String,Object> data = snapshot.getData();
                        if (data != null){
                            Log.d("Firestore", "Data Map: " + data.toString());
                            String turAdi = (String) data.get("TürAdı");
                            String foto = (String) data.get("Foto");

                            Tur tur = new Tur(turAdi,foto);
                            turArrayList.add(tur);
                        }
                    }
                    turAdapter.notifyDataSetChanged();
                }
            }
        });

    }


   public void Home(){
     //auth.signOut();
       Intent intent1 = new Intent(AnaSayfaActivity.this,AnaSayfaActivity.class);
       startActivity(intent1);
   }
   public void Question(){
     Intent intent1 = new Intent(AnaSayfaActivity.this,SorguActivity.class);
     startActivity(intent1);
   }
   public void Users(){
        Intent intent1 = new Intent(AnaSayfaActivity.this,KullaniciActivity.class);
        startActivity(intent1);
   }




    @Override
    public void onItemClick(int position) {
         Intent intent = new Intent(AnaSayfaActivity.this, TurActivity.class);
         intent.putExtra("TürAdı",turArrayList.get(position).getTurAdi());
         startActivity(intent);

    }


}