/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rabbitmq;

import java.util.List;

/**
 *
 * @author Elkur
 */
public class ReporteDTO {

    private String defecto;
    private int numPiezas;
    private double costoPorPiezas;
    private List<String> detalles;

    public ReporteDTO() {
    }
    
    public ReporteDTO(String defecto, int numPiezas, double costoPorPiezas, List<String> detalles) {
        this.defecto = defecto;
        this.numPiezas = numPiezas;
        this.costoPorPiezas = costoPorPiezas;
        this.detalles = detalles;
    }
    
    public String getDefecto() {
        return defecto;
    }
    
    public void setDefecto(String defecto) {
        this.defecto = defecto;
    }

    public int getNumPiezas() {
        return numPiezas;
    }

    public void setNumPiezas(int numPiezas) {
        this.numPiezas = numPiezas;
    }

    public double getCostoPorPiezas() {
        return costoPorPiezas;
    }

    public void setCostoPorPiezas(double costoPorPiezas) {
        this.costoPorPiezas = costoPorPiezas;
    }

    public List<String> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<String> detalles) {
        this.detalles = detalles;
    }

}
