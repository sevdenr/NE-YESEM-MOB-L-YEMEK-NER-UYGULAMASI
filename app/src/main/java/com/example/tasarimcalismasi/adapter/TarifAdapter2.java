package com.example.tasarimcalismasi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasarimcalismasi.databinding.Tarif2ViewBinding;
import com.example.tasarimcalismasi.databinding.TarifViewBinding;
import com.example.tasarimcalismasi.model.Tarif2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TarifAdapter2 extends RecyclerView.Adapter<TarifAdapter2.TarifHolder2> {
    private ArrayList<Tarif2> tarif2ArrayList;
    private final OnItemClickListener onItemClickListener;

    public TarifAdapter2(ArrayList<Tarif2> tarif2ArrayList, OnItemClickListener onItemClickListener){
        this.tarif2ArrayList = tarif2ArrayList;
        this.onItemClickListener = onItemClickListener;

    }
    @NonNull
    @Override
    public TarifHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tarif2ViewBinding tarif2ViewBinding = Tarif2ViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new TarifHolder2(tarif2ViewBinding,  onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TarifHolder2 Holder, int position) {
        Holder.tarif2ViewBinding.textViewTarifAdi.setText((CharSequence) tarif2ArrayList.get(position).tarifAdi);
        Holder.tarif2ViewBinding.textViewTur.setText((CharSequence) tarif2ArrayList.get(position).tur);
        Holder.tarif2ViewBinding.textViewSure.setText((CharSequence) tarif2ArrayList.get(position).sure);
        Holder.tarif2ViewBinding.textViewMalzeme.setText((CharSequence) tarif2ArrayList.get(position).malzemeler);
        Holder.tarif2ViewBinding.textViewTarif.setText((CharSequence) tarif2ArrayList.get(position).tarif);
        Picasso.get().load(tarif2ArrayList.get(position).yemek2Foto).into(Holder.tarif2ViewBinding.imageViewTarif2);

    }

    @Override
    public int getItemCount() {
        if(tarif2ArrayList != null){
            return  tarif2ArrayList.size();
        }else{
            return 0;
        }

    }
    class TarifHolder2 extends RecyclerView.ViewHolder {
        private final Tarif2ViewBinding tarif2ViewBinding;
        public TarifHolder2(Tarif2ViewBinding tarif2ViewBinding,OnItemClickListener onItemClickListener){
            super(tarif2ViewBinding.getRoot());
            this.tarif2ViewBinding = tarif2ViewBinding;
            tarif2ViewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TarifAdapter2.this.onItemClickListener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            TarifAdapter2.this.onItemClickListener.onItemClick(pos);
                        }

                    }
                }
            });

        }

    }
}
