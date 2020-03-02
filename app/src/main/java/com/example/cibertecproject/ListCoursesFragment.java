package com.example.cibertecproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cibertecproject.Course.ApiClient;
import com.example.cibertecproject.Course.ApiCourseService;
import com.example.cibertecproject.Course.Course;
import com.example.cibertecproject.Course.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListCoursesFragment extends Fragment {

    private RecyclerView recyCursos;
    private CourseAdapter cursoAdapter;
    private List<Course> lstCurso;

    public ListCoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_list_courses, container, false);

        recyCursos = rootView.findViewById(R.id.RecyListCourses);
        recyCursos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        lstCurso = new ArrayList<>();
        cursoAdapter = new CourseAdapter(lstCurso);
        recyCursos.setAdapter(cursoAdapter);

        CallService();
        return rootView;

    }

    public static ListCoursesFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ListCoursesFragment fragment = new ListCoursesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void CallService() {
        ApiCourseService ApiCourseClient = ApiClient.getApiClient().create(ApiCourseService.class);
        Call<List<Course>> call = ApiCourseClient.getCursos();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                List<Course> lstCourses = response.body();
                lstCurso.addAll(lstCourses);
                cursoAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
