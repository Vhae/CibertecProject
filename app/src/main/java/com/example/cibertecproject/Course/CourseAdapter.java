package com.example.cibertecproject.Course;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cibertecproject.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> lstCurso;

    public CourseAdapter(List<Course> lstCurso) {
        super();
        this.lstCurso = lstCurso;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course,parent,false);

        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course oCur = lstCurso.get(position);
        holder.txtIdCurso.setText( "Código : " + String.valueOf( oCur.getId_Curso() ));
        holder.txtNombre.setText( "Nombre : " + oCur.getNombre());
    }

    @Override
    public int getItemCount() {
        return lstCurso.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        private TextView txtIdCurso ;
        private TextView txtNombre ;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIdCurso = itemView.findViewById(R.id.txtIdCurso);
            txtNombre = itemView.findViewById(R.id.txtNombre);

        }
    }

}

