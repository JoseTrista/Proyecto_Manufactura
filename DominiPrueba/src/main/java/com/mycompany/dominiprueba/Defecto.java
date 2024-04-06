/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominiprueba;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jctri
 */
public class Defecto {
    private long id;
    private String NumeroLote;
    private String detalles;
    private String defectos;

    public Defecto() {
    }

    public Defecto(long id, String NumeroLote, String detalles) {
        this.id = id;
        this.NumeroLote = NumeroLote;
        this.detalles = detalles;
    }

    public Defecto(String NumeroLote, String detalles, String defectos) {
        this.NumeroLote = NumeroLote;
        this.detalles = detalles;
        this.defectos = defectos;
    }

    public String getNumeroLote() {
        return NumeroLote;
    }

    public void setNumeroLote(String NumeroLote) {
        this.NumeroLote = NumeroLote;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getDefectos() {
        return defectos;
    }

    public void setDefectos(String defectos) {
        this.defectos = defectos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Defecto other = (Defecto) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Defecto{" + "id=" + id + ", NumeroLote=" + NumeroLote + ", detalles=" + detalles + ", defectos=" + defectos + '}';
    }

    
}
