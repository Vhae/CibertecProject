package com.example.cibertecproject.Modelo;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

public class Evento {

    int codigoevento;

    String nombreevento;

    String descripcion;

    String ubicacionlatitud;

    String ubicacionlongitud;

    String fecha;

    String hora;

    String codigopersona;

    String codigocurso;


    public int getCodigoevento() {
        return codigoevento;
    }

    public void setCodigoevento(int codigoevento) {
        this.codigoevento = codigoevento;
    }

    public String getNombreevento() {
        return nombreevento;
    }

    public void setNombreevento(String nombreevento) {
        this.nombreevento = nombreevento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacionlatitud() {
        return ubicacionlatitud;
    }

    public void setUbicacionlatitud(String ubicacionlatitud) {
        this.ubicacionlatitud = ubicacionlatitud;
    }

    public String getUbicacionlongitud() {
        return ubicacionlongitud;
    }

    public void setUbicacionlongitud(String ubicacionlongitud) {
        this.ubicacionlongitud = ubicacionlongitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCodigopersona() {
        return codigopersona;
    }

    public void setCodigopersona(String codigopersona) {
        this.codigopersona = codigopersona;
    }

    public String getCodigocurso() {
        return codigocurso;
    }

    public void setCodigocurso(String codigocurso) {
        this.codigocurso = codigocurso;
    }


    public Evento(int codigoevento, String nombreevento, String descripcion, String ubicacionlatitud, String ubicacionlongitud, String fecha, String hora, String codigopersona, String codigocurso) {
        this.codigoevento = codigoevento;
        this.nombreevento = nombreevento;
        this.descripcion = descripcion;
        this.ubicacionlatitud = ubicacionlatitud;
        this.ubicacionlongitud = ubicacionlongitud;
        this.fecha = fecha;
        this.hora = hora;
        this.codigopersona = codigopersona;
        this.codigocurso = codigocurso;
    }

    @Override
    public String toString() {
        return codigocurso;
    }
}
