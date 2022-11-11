package com.example.laboratorios.model;

import android.media.Image;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;

public abstract class Alojamiento implements Serializable {

    //Atributos
    protected Integer id;
    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;
    protected Double precioBase;
    protected Boolean favorito;

    //Constructores
    public Alojamiento(){
        super();
    }

    public Alojamiento(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.favorito = false;
    }

    //Getters y Setters
    public abstract Ubicacion getUbicacion();

    public Double costoDia(){
        return precioBase;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
