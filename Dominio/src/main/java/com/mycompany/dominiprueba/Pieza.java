/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominiprueba;

/**
 *
 * @author jctri
 */
public class Pieza {
    private long id;
    private String nombrePieza;
    private int precio;

    public Pieza() {
    }

    public Pieza(long id, String nombrePieza, int precio) {
        this.id = id;
        this.nombrePieza = nombrePieza;
        this.precio = precio;
    }

    public Pieza(String nombrePieza, int precio) {
        this.nombrePieza = nombrePieza;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pieza other = (Pieza) obj;
        return this.id == other.id;
    }

    
    @Override
    public String toString() {
        return "Pieza{" + "id=" + id + ", nombrePieza=" + nombrePieza + ", precio=" + precio + '}';
    }
}
