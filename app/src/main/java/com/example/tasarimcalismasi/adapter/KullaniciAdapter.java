package com.example.tasarimcalismasi.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasarimcalismasi.databinding.KullaniciViewBinding;
import com.example.tasarimcalismasi.model.Kullanici;

import java.util.ArrayList;

public class KullaniciAdapter extends RecyclerView.Adapter<KullaniciAdapter.KullaniciHolder> {
    ArrayList<Kullanici> kullaniciArrayList;

    public KullaniciAdapter(ArrayList<Kullanici> kullaniciArrayList){
        this.kullaniciArrayList = kullaniciArrayList;
    }

    @NonNull
    @Override
    public KullaniciAdapter.KullaniciHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        KullaniciViewBinding kullaniciViewBinding = KullaniciViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new KullaniciHolder(kullaniciViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull KullaniciAdapter.KullaniciHolder holder, int position) {
        holder.kullaniciViewBinding.kullaniciAdi.setText((CharSequence)kullaniciArrayList.get(position).kullaniciAdi);
        holder.kullaniciViewBinding.textkullaniciSoyadi.setText((CharSequence)kullaniciArrayList.get(position).KullaniciSoyadi);
        holder.kullaniciViewBinding.EmailKullanici.setText((CharSequence)kullaniciArrayList.get(position).Email);
    }

    @Override
    public int getItemCount() {
        if (kullaniciArrayList != null){
            return kullaniciArrayList.size();
        }else{
            return 0;
        }

    }

    class KullaniciHolder extends RecyclerView.ViewHolder {
        private final KullaniciViewBinding kullaniciViewBinding;
        public KullaniciHolder(KullaniciViewBinding kullaniciViewBinding){
            super(kullaniciViewBinding.getRoot());
            this.kullaniciViewBinding = kullaniciViewBinding;
        }
    }
}
