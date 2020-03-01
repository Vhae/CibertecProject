package com.example.cibertecproject.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCourseClient {

    @GET("getCursos")
    Call<List<Course>> getCursos();


}
