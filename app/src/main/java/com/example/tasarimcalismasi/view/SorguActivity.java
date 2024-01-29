package com.example.tasarimcalismasi.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.tasarimcalismasi.R;
import com.example.tasarimcalismasi.adapter.MalzemeAdapter;
import com.example.tasarimcalismasi.databinding.ActivitySorguBinding;
import com.example.tasarimcalismasi.model.Malzeme;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class SorguActivity extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private ActivitySorguBinding binding;

    ArrayList<Malzeme> malzemeArrayList;

    MalzemeAdapter malzemeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySorguBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerViewSorgu = findViewById(R.id.recyclerViewSorgu);
        int spanCount = 2; // İki sütunlu bir düzen
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerViewSorgu.setLayoutManager(layoutManager);



        malzemeArrayList = new ArrayList<>();
        malzemeAdapter = new MalzemeAdapter(malzemeArrayList);
        recyclerViewSorgu.setAdapter(malzemeAdapter);
        getData();
        showErrorDialog("Maksimum 10 öğe seçilebilir");
    }

    private  void getData(){
        firebaseFirestore.collection("Malzemeler").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Toast.makeText(SorguActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if(value != null){
                    malzemeArrayList.clear();
                    for (DocumentSnapshot snapshot: value.getDocuments()){
                        Map<String,Object> data = snapshot.getData();
                        if (data != null){
                            String malzemeAdi = (String) data.get("MalzemeAdı");

                            Malzeme malzeme = new Malzeme(malzemeAdi);
                            malzemeArrayList.add(malzeme);
                        }
                    }
                    malzemeAdapter.notifyDataSetChanged();
                }
            }
        });

    }
    private void showErrorDialog(String errorMessage) {
        new AlertDialog.Builder(this)
                //.setTitle("Dikkat")
                .setMessage(errorMessage)
                .setPositiveButton("Tamam", null)
                .show();
    }
}