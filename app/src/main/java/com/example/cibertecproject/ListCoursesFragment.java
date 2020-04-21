package com.example.cibertecproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cibertecproject.Course.ApiClient;
import com.example.cibertecproject.Course.ApiCourseService;
import com.example.cibertecproject.Course.Course;
import com.example.cibertecproject.Course.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListCoursesFragment extends Fragment  {

    private RecyclerView recyCursos;
    private CourseAdapter cursoAdapter;
    private List<Course> lstCurso;
    private TextView txtResument;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    public static ListCoursesFragment newInstance(){

        Bundle args = new Bundle();

        ListCoursesFragment fragment = new ListCoursesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ListCoursesFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_list_courses, container, false);

        txtResument = rootView.findViewById(R.id.txtResumen);
        recyCursos = rootView.findViewById(R.id.RecyListCourses);
        recyCursos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        lstCurso = new ArrayList<>();

        cursoAdapter = new CourseAdapter(lstCurso);
        cursoAdapter.setOnItemClicListener(new CourseAdapter.OnItemClicListener() {
            @Override
            public void onItemClic(int posistion) {
                setActualizarLista();
            }
        });
        recyCursos.setAdapter(cursoAdapter);
        CallService();
        return rootView;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



//Lista desde el servicio
    private void CallService() {
        ApiCourseService ApiCourseClient = ApiClient.getApiClient().create(ApiCourseService.class);
        Call<List<Course>> call = ApiCourseClient.getCursosMySql();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                response.body();
                List<Course> lstCourses = response.body();
                lstCurso.addAll(lstCourses);
                cursoAdapter.notifyDataSetChanged();
                txtResument.setText( "Cantidad de cursos :" + lstCourses.size() );
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        int r =0;
        int x= 0;
    }

    public void setActualizarLista(){
        lstCurso.clear();
        cursoAdapter.notifyDataSetChanged();
        CallService();
    }



}
