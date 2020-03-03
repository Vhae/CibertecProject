package com.example.cibertecproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cibertecproject.Modelo.UserEvents;
import com.example.cibertecproject.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterAttendance extends RecyclerView.Adapter<AdapterAttendance.ViewHolder> {
    private List<UserEvents> lstUserEvents;
    private Context context;

    public AdapterAttendance(List<UserEvents> lstUserEvents, Context context) {
        this.lstUserEvents = lstUserEvents;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.item_list_attendance,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserEvents userEvents=lstUserEvents.get(position);
        holder.txtv_Evento.setText(userEvents.getNombres());
        holder.txtv_Curso.setText(userEvents.getNombcurso());
        holder.txtv_Fecha.setText(userEvents.getFecha());
    }

    @Override
    public int getItemCount() {
        return lstUserEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtv_Evento,txtv_Curso,txtv_Fecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtv_Evento=itemView.findViewById(R.id.txt_attend_Evento);
            txtv_Curso=itemView.findViewById(R.id.txt_attend_curso);
            txtv_Fecha=itemView.findViewById(R.id.txt_attend_fecha);
        }
    }
}
