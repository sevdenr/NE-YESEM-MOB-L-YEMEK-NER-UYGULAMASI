/*package com.example.tasarimcalismasi.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasarimcalismasi.R;
import com.example.tasarimcalismasi.adapter.TarifAdapter;
import com.example.tasarimcalismasi.adapter.TarifAdapter2;
import com.example.tasarimcalismasi.databinding.ActivityOnerilenYemekBinding;
import com.example.tasarimcalismasi.model.Malzeme;
import com.example.tasarimcalismasi.model.Tarif;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class OnerilenYemek extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private ActivityOnerilenYemekBinding binding;
    ArrayList<Malzeme> sorguArraylist;
    ArrayList<Tarif> oneriArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnerilenYemekBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewTarif);
        int spanCount=1;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager( layoutManager);

        sorguArraylist = new ArrayList<>();
        oneriArraylist = new ArrayList<>();
        TarifAdapter tarifAdapterOneri = new TarifAdapter(oneriArraylist, this);
        recyclerView.setAdapter(tarifAdapterOneri);
        getData();

    }
    private <sorguArraylist> void getData(){
        for( Malzeme eleman : sorguArraylist){
            firebaseFirestore.collection("Tarif").whereEqualTo("Malzemeler", eleman).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (error != null){
                        Toast.makeText(OnerilenYemek.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                    if(value != null){
                        oneriArraylist.clear();
                        for (DocumentSnapshot snapshot: value.getDocuments()){
                            Map<String,Object> data = snapshot.getData();
                            if (data != null){
                                String tarifAdi = (String) data.get("TarifAdı");
                                String yemekFoto = (String) data.get("YemekFoto");

                                Tarif oneri = new Tarif(tarifAdi, yemekFoto);

                                oneriArraylist.add(oneri);
                            }
                        }
                        tarifAdapterOneri.notifyDataSetChanged();
                    }
                }
            });
        }

    }
}*/