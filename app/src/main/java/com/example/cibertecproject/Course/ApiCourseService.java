package com.example.cibertecproject.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

public interface ApiCourseService {

    @GET("getCursos")
    Call<List<Course>> getCursos();

    @DELETE("deletesCourses")
    Call<Integer> deleteCourses();


}
