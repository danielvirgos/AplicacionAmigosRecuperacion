package com.example.aplicacionamigosrecuperacion.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Amigo {

    private String nombre;

    private String telefono;

    private String fechaNac;

    public Amigo() {
        this("", "", "");
    }

    public Amigo(@NonNull String nm, @NonNull String tl) {
        this.nombre = nm;
        this.telefono = tl;
    }

    public Amigo(@NonNull String nm, @NonNull String tl, @NonNull String fn) {
        this.nombre = nm;
        this.telefono = tl;
        this.fechaNac = fn;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    @NonNull
    public String  getTelefono() {
        return telefono;
    }

    @NonNull
    public String getFechaNac() {
        return fechaNac;
    }


    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(@NonNull String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNac(@NonNull String fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return "Amigo{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                '}';
    }
}
