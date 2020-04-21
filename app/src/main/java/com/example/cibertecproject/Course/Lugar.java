package com.example.cibertecproject.Course;

import androidx.annotation.NonNull;

public class Lugar {

    private int Id;
    private String Nombre;
    private float Latitud;
    private float Longitud;
    private int Estado;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public float getLatitud() {
        return Latitud;
    }

    public void setLatitud(float latitud) {
        Latitud = latitud;
    }

    public float getLongitud() {
        return Longitud;
    }

    public void setLongitud(float longitud) {
        Longitud = longitud;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
