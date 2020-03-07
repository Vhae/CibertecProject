package com.example.cibertecproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cibertecproject.Modelo.Evento;

import java.util.List;

public class RecyclerCursoPersona extends RecyclerView.Adapter<ViewHolderCursoPersona> {

    private List<Evento> eventList;

    public RecyclerCursoPersona(List<Evento> listaevento){
        this.eventList = listaevento;
    }


    @NonNull
    @Override
    public ViewHolderCursoPersona onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cursos_persona,parent,false);
        return new ViewHolderCursoPersona(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCursoPersona holder, int position) {
        holder.bind(eventList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
