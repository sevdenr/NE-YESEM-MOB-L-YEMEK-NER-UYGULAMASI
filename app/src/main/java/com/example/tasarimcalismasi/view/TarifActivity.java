package com.example.tasarimcalismasi.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasarimcalismasi.R;
import com.example.tasarimcalismasi.adapter.OnItemClickListener;
import com.example.tasarimcalismasi.adapter.TarifAdapter2;
import com.example.tasarimcalismasi.databinding.ActivityTarifBinding;
import com.example.tasarimcalismasi.model.Tarif2;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class TarifActivity extends AppCompatActivity implements OnItemClickListener {
    private ActivityTarifBinding binding;
    private FirebaseFirestore firebaseFirestore;
    TarifAdapter2 tarifAdapter2;
    ArrayList<Tarif2> tarif2ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTarifBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewTarif);
        int spanCount=1;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager( layoutManager);

        tarif2ArrayList = new ArrayList<>();
        tarifAdapter2 = new TarifAdapter2(tarif2ArrayList,this);
        recyclerView.setAdapter(tarifAdapter2);

        Intent intent = getIntent();
        String tarifadi = getIntent().getStringExtra("TarifAdı");
        TextView textView = binding.textTarifAdi;
        textView.setText(tarifadi);

        getData(tarifadi);
    }
    private void getData(String tarifAdi) {

        firebaseFirestore.collection("Tarif").whereEqualTo("TarifAdı", tarifAdi)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(TarifActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if (value != null) {
                            tarif2ArrayList.clear();
                            for (DocumentSnapshot snapshot : value.getDocuments()) {
                                Log.d("Firestore", "Snapshot Data: " + snapshot.getData());
                                Map<String, Object> data = snapshot.getData();
                                if (data != null) {
                                    Log.d("Firestore", "Data Map: " + data.toString());
                                    // Sadece TarifAdı ve YemekFoto alanlarını al
                                    String tarifAdi = (String) data.get("TarifAdı");
                                    String yemek2Foto = (String) data.get("YemekFoto");
                                    String sure = (String) data.get("Süre");
                                    String tur = (String) data.get("Tür");
                                    String malzeme = (String) data.get("Malzemeler");
                                    String tarif = (String) data.get("Tarif");

                                    Tarif2 tarif2 = new Tarif2(tarifAdi, yemek2Foto,malzeme,tarif,sure,tur);
                                    tarif2ArrayList.add(tarif2);
                                }
                            }
                            tarifAdapter2.notifyDataSetChanged();
                        }
                    }
                });
    }
    public void TextViewFonk(View view){

        finish();

    }

    @Override
    public void onItemClick(int position) {
        Intent intent2 = new Intent(TarifActivity.this, TurActivity.class);
        intent2.putExtra("Tür",tarif2ArrayList.get(position).getTarifAdi());
        startActivity(intent2);
        finish();
    }
}