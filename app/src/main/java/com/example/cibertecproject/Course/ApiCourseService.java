package com.example.cibertecproject.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiCourseService {

    //Listar todos los Cursos
    @GET("getCursosMySql")
    Call<List<Course>> getCursosMySql();

    //Eliminar Curso
    @DELETE("delCursoMySql/{Id_Curso}")
    Call<Integer> deleteCourse( @Path("Id_Curso") int Id_Curso );

    //Actualizar Curso
    @PUT("updCourseMySql")
    Call<Integer> updateCourse( @Body Course course );
    //Listar Sede
    @GET("getLugar")
    Call<ResponseRest> getLugar();
    //Registrar Curso
    @POST("regCourseMySql")
    Call<Integer> registCourse(@Body Course course);



    @PUT("updCourseMySql/{Id_Curso}/{nombre}/{descripcion}")
    Call<Integer> updCourseMySql(
            @Path("Id_Curso") String Id_Curso,
            @Path("nombre") String nombre,
            @Path("descripcion") String descripcion
    );


    @POST("posts")
    @FormUrlEncoded
    Call<Course> saveCourse(
            @Field("Id_Curso") String title,
            @Field("body") String body,
            @Field("userId") int userId
    );

}
