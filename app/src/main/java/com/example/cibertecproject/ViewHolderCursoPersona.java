package com.example.cibertecproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cibertecproject.Modelo.Evento;

public class ViewHolderCursoPersona extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtpersonarecycler, txtcursorecycler;
    ImageView imgDelete;

    public ViewHolderCursoPersona(@NonNull View itemView) {
        super(itemView);
        txtpersonarecycler = itemView.findViewById(R.id.rippersona);
        txtcursorecycler = itemView.findViewById(R.id.ripcurso);
        imgDelete=itemView.findViewById(R.id.imgDelete);
        imgDelete.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    void bind(Evento listaeventos) {
        txtpersonarecycler.setText("Persona: " + listaeventos.getCodigopersona());
        txtcursorecycler.setText("Curso: " + listaeventos.getCodigocurso());
    }


    @Override
    public void onClick(View view) {
        int v = view.getId();
        if (v == R.id.imgDelete) {
            switch (view.getId()) {
                case R.id.imgDelete:
                    // Se elimina
                    Toast.makeText(view.getContext(), "Se Elimina uno", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
