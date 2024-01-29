package com.example.tasarimcalismasi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasarimcalismasi.databinding.MalzemeViewBinding;
import com.example.tasarimcalismasi.model.Malzeme;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MalzemeAdapter extends RecyclerView.Adapter <MalzemeAdapter.MalzemeHolder>{
    private ArrayList<Malzeme> sorguArraylist;

    @NonNull
    private ArrayList<Malzeme> malzemeArrayList;
    //private final
    public MalzemeAdapter(ArrayList<Malzeme> malzemeArrayList){
        this.malzemeArrayList=malzemeArrayList;
        this.sorguArraylist= new ArrayList<>();
    }
    @Override
    public MalzemeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MalzemeViewBinding malzemeViewBinding =MalzemeViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MalzemeHolder(malzemeViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MalzemeAdapter.MalzemeHolder holder, int position) {
        holder.malzemeViewBinding.textViewMalzeme.setText((CharSequence) malzemeArrayList.get(position).malzemeAdi);
        Malzeme currentMalzeme = malzemeArrayList.get(position);

        // CheckBox durumunu güncelle
        holder.checkBox.setChecked(currentMalzeme.isSelected());

        // Malzeme adını ayarla
        holder.malzemeViewBinding.textViewMalzeme.setText(currentMalzeme.getMalzemeAdi());


    }
    public ArrayList<Malzeme> sorguArraylist() {
        return sorguArraylist;
    }
    @Override
    public int getItemCount() {
        return malzemeArrayList == null ?0 : malzemeArrayList.size();
    }

    public class MalzemeHolder extends RecyclerView.ViewHolder{
        private final MalzemeViewBinding malzemeViewBinding;
        private final CheckBox checkBox;
        private  int selectedCount=0, MAX_SELECTION=10;
        public MalzemeHolder(MalzemeViewBinding malzemeViewBinding){
            super(malzemeViewBinding.getRoot());
            this.malzemeViewBinding = malzemeViewBinding;
            checkBox = malzemeViewBinding.checkBoxMalzeme;

            // CheckBox durumu değiştiğinde olay dinleyicisi ekle
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Malzeme öğesinin seçim durumunu güncelle
                        malzemeArrayList.get(position).setSelected(isChecked);

                        // Seçilen öğe sayısını kontrol et
                        if (isChecked) {
                            sorguArraylist.add(malzemeArrayList.get(position)); // Seçili listeye ekle
                        } else {
                            sorguArraylist.remove(malzemeArrayList.get(position)); // Seçili listeden çıkar
                        }
                    }
                }
            });

            // Öğe tıklanınca da seçim durumunu güncelle
            malzemeViewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBox.setChecked(!checkBox.isChecked());
                }
            });
           
        }

    }
}
