/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.dao_manufactura;

import com.mycompany.dominiprueba.Defecto;

/**
 *
 * @author jctri
 */
public class PRUEBASGG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Defecto d1 = new Defecto();
        d1.setId(10);
        d1.setNumeroLote("500");
        d1.setDefectos("Pieza Rota");
        d1.setDetalles("el diego");
        d1.setId_pieza(1);
        
        DaoDefecto dao = new DaoDefecto("localhost", "3306", "manufactura", "root", "Movagro123.,");
        dao.agregarDefecto(d1);
    }
    
}
