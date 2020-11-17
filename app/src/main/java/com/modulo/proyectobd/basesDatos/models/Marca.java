package com.modulo.proyectobd.basesDatos.models;

public class Marca {
    String nombre;
    int miniatura;

    public Marca(String nombre, int miniatura){

        this.nombre = nombre;
        this.miniatura= miniatura;
    }

    public int getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(int miniatura) {
        this.miniatura = miniatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "nombre='" + nombre + '\'' +
                "miniatura='" + miniatura + '\'' +
                '}';
    }
}

