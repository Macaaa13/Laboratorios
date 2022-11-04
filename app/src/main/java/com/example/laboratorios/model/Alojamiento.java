package com.example.laboratorios.model;

public abstract class Alojamiento {

    //Atributos
    protected Integer id;
    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;
    protected Double precioBase;

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
    }

    public abstract Ubicacion getUbicacion();

    public Double costoDia(){
        return precioBase;
    }
}
