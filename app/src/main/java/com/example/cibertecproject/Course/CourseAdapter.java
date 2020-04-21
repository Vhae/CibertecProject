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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cibertecproject.AddCourseEventActivity;
import com.example.cibertecproject.EventCreateEditActivity;
import com.example.cibertecproject.ListCoursesFragment;
import com.example.cibertecproject.ListExpositorsFragment;
import com.example.cibertecproject.MainActivity;
import com.example.cibertecproject.R;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>
        {
    private List<Course> lstCurso;


    private OnItemClicListener onItemClicListener;

    public interface OnItemClicListener{
        void onItemClic(int posistion);
    }

    public void setOnItemClicListener(OnItemClicListener onItemClicListener){
        this.onItemClicListener = onItemClicListener ;
    }


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
        holder.txtIdCurso.setText( String.valueOf( oCur.getId_Curso() ));
        holder.txtNombre.setText( oCur.getNombre());
        holder.txtNameCourse.setText(oCur.getDescripcion());
        holder.txtEstado.setText( String.valueOf( oCur.getEstado()));
        holder.txtFechaInicio.setText(  strDate ( oCur.getFechaInicio() ));

    }

    @Override
    public int getItemCount() {
        return lstCurso.size();
    }

    private String strDate(Date date){
        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
        return sdformat.format(date);
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtIdCurso ;
        private TextView txtNombre ;
        private TextView txtDescripcion;
        private TextView txtNameCourse, txtEstado;
        private ImageView imgvEditCourse, imgvDeleteCourse;
        private TextView txtFechaInicio;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIdCurso = itemView.findViewById(R.id.txtIdCurso);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtNameCourse = itemView.findViewById(R.id.txtNameCourse);
            imgvDeleteCourse = itemView.findViewById(R.id.imgDeleteCourse);
            imgvEditCourse = itemView.findViewById(R.id.imgEditCourse);
            txtEstado = itemView.findViewById(R.id.txtEstado);
            txtFechaInicio = itemView.findViewById(R.id.txtFechaInicio);
            imgvEditCourse.setOnClickListener(this);
            imgvDeleteCourse.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            int v=view.getId();
            if(v==R.id.imgDeleteCourse||v==R.id.imgEditCourse){
                switch(view.getId()){
                    case R.id.imgEditCourse: //Editar Curso
                        Course curso = lstCurso.get(mPosition);
                        Intent addintent=new Intent(view.getContext(),AddCourseEventActivity.class);
                        addintent.putExtra("opcion","1");
                        addintent.putExtra("edit_curso", Parcels.wrap(curso));
                        view.getContext().startActivity(addintent);
                        break;
                    case R.id.imgDeleteCourse:
                        // Se elimina un curso
                        int Id_Curso =  Integer.parseInt( txtIdCurso.getText().toString());
                        deleteCourse(Id_Curso);
                        onItemClicListener.onItemClic(getAdapterPosition());
                        Toast.makeText(view.getContext(), "El curso fue eliminado", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        }

//Eliminar desde WCF
        private void deleteCourse(int Id_Curso){
            ApiCourseService apiCourseService = ApiClient.getApiClient().create(ApiCourseService.class);
            //String pId_Curso = String.valueOf(Id_Curso);
            Call<Integer> call = apiCourseService.deleteCourse(Id_Curso);
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    int resDelete = response.body();
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }





    }


}

