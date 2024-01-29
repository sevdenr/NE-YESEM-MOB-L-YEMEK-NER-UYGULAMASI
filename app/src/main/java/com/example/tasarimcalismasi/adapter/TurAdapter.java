package com.example.tasarimcalismasi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasarimcalismasi.databinding.YemekViewBinding;
import com.example.tasarimcalismasi.model.Tur;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TurAdapter extends RecyclerView.Adapter<TurAdapter.TurHolder>{
    private ArrayList<Tur> turArrayList;
    private final OnItemClickListener onItemClickListener;

    public TurAdapter(ArrayList<Tur> turArrayList, OnItemClickListener onItemClickListener){
        this.turArrayList = turArrayList;
        this.onItemClickListener = onItemClickListener;

    }


    // Setter method for the listener


    @NonNull
    @Override
    public TurHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        YemekViewBinding yemekViewBinding = YemekViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new TurHolder(yemekViewBinding, onItemClickListener);
    }
    public void onBindViewHolder(@NonNull TurHolder turHolder, int i) {

        turHolder.yemekViewBinding.textView3.setText((CharSequence) turArrayList.get(i).turAdi);
        Picasso.get()
                .load(turArrayList.get(i).foto)
                .into(turHolder.yemekViewBinding.imageView3);
    }
    public int getItemCount() {
        return turArrayList == null ? 0 : turArrayList.size();
    }

    public class TurHolder extends RecyclerView.ViewHolder {

        private final YemekViewBinding yemekViewBinding;

        public TurHolder(YemekViewBinding yemekViewBinding, OnItemClickListener onItemClickListener) {
            super(yemekViewBinding.getRoot());
            this.yemekViewBinding = yemekViewBinding;
            yemekViewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(pos);
                        }

                    }
                }
            });
        }

    }

}

