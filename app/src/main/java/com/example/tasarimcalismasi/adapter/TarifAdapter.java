package com.example.tasarimcalismasi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasarimcalismasi.databinding.TarifViewBinding;
import com.example.tasarimcalismasi.model.Tarif;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TarifAdapter extends RecyclerView.Adapter<TarifAdapter.TarifHolder> {
   private ArrayList<Tarif> tarifArrayList;
   private final OnItemClickListener onItemClickListener;
   public TarifAdapter(ArrayList<Tarif> tarifArrayList, OnItemClickListener onItemClickListener){
       this.tarifArrayList = tarifArrayList;
       this.onItemClickListener = onItemClickListener;
   }
    @NonNull
    @Override
    public TarifHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TarifViewBinding tarifViewBinding = TarifViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

       return new TarifHolder(tarifViewBinding, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TarifHolder tarifHolder, int position) {
        tarifHolder.tarifViewBinding.textViewTarif.setText((CharSequence) tarifArrayList.get(position).tarifAdi);
        Picasso.get().load(tarifArrayList.get(position).yemekFoto).into(tarifHolder.tarifViewBinding.imageViewTarif);

    }

    @Override
    public int getItemCount() {
       if(tarifArrayList != null){
           return  tarifArrayList.size();
       }else{
           return 0;
       }

    }
    class TarifHolder extends RecyclerView.ViewHolder {
       private final TarifViewBinding tarifViewBinding;
       public TarifHolder(TarifViewBinding tarifViewBinding, OnItemClickListener onItemClickListener){
           super(tarifViewBinding.getRoot());
           this.tarifViewBinding = tarifViewBinding;
           tarifViewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (TarifAdapter.this.onItemClickListener != null){
                       int pos = getAdapterPosition();
                       if(pos != RecyclerView.NO_POSITION){
                           TarifAdapter.this.onItemClickListener.onItemClick(pos);
                       }

                   }
               }
           });
       }

    }
}
