package com.example.cibertecproject.Course;


import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.sql.Time;
import java.util.Date;

@Parcel
public class Course {

    @SerializedName("Id_Curso")
    private int Id_Curso;
    private String Nombre;
    private String Descripcion;

    //@SerializedName("FechaIni")
    private Date FechaInicio;
    //@SerializedName("HoraIni")
    private Time HoraInicio;
    //@SerializedName("HoraFi")
    private Time HoraFin;
    private int Id_Lugar;

    public Time getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        HoraInicio = horaInicio;
    }

    public Time getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(Time horaFin) {
        HoraFin = horaFin;
    }

    public int getId_Lugar() {
        return Id_Lugar;
    }

    public void setId_Lugar(int id_Lugar) {
        Id_Lugar = id_Lugar;
    }

    private float Costo;
    private int Estado;

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }


    public float getCosto() {
        return Costo;
    }

    public void setCosto(float costo) {
        Costo = costo;
    }



    public int getId_Curso() {
        return Id_Curso;
    }

    public void setId_Curso(int id_Curso) {
        Id_Curso = id_Curso;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }


}
