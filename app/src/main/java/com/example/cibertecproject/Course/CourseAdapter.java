package com.example.cibertecproject.Course;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cibertecproject.AddCourseEventActivity;
import com.example.cibertecproject.EventCreateEditActivity;
import com.example.cibertecproject.MainActivity;
import com.example.cibertecproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.txtNombre.setText( oCur.getNombre());
        holder.txtNameCourse.setText(oCur.getNombre());
        holder.txtEstado.setText( String.valueOf( oCur.isEstado()));
    }

    @Override
    public int getItemCount() {
        return lstCurso.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        private TextView txtIdCurso ;
        private TextView txtNombre ;
        private TextView txtDescripcion;
        private TextView txtNameCourse, txtEstado;
        private ImageView imgvEditCourse, imgvDeleteCourse;


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIdCurso = itemView.findViewById(R.id.txtIdCurso);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtNameCourse = itemView.findViewById(R.id.txtNameCourse);
            imgvDeleteCourse = itemView.findViewById(R.id.imgDeleteCourse);
            txtEstado = itemView.findViewById(R.id.txtEstado);

            imgvDeleteCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteCourse(v);

                }
            });

        }

        private void deleteCourse(View v){
            ApiCourseService apiCourseService = ApiClient.getApiClient().create(ApiCourseService.class);
            Call<Integer> call = apiCourseService.deleteCourses();
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {

                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }




    }


}

