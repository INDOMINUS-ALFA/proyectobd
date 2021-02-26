package com.modulo.proyectobd.basesDatos.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Ubicaciones")
public class Ubicacion {
    @PrimaryKey @NonNull private String nombre;
    @ColumnInfo @NonNull private String descripcion;
    @ColumnInfo @NonNull private double latitud;
    @ColumnInfo @NonNull private double longitud;
    @ColumnInfo @NonNull private double altura;
    @ColumnInfo @NonNull private double precision;

    public Ubicacion(String nombre, String descripcion, double latitud, double longitud, double altura, double precision) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altura = altura;
        this.precision = precision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(Float precision) {
        this.precision = precision;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", altura=" + altura +
                ", precision=" + precision +
                '}';
    }
}