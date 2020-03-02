package com.example.cibertecproject.Course;

public class Course {

    private int Id_Curso;
    private String Nombre;
    private String Descripcion;
    private boolean Estado;

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


    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }
}
