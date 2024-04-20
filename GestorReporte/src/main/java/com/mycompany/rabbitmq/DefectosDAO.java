/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rabbitmq;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elkur
 */
public class DefectosDAO {

    private RabbitPublisher rp;

    public DefectosDAO() {
        try {
            rp = new RabbitPublisher();
        } catch (IOException ex) {
            Logger.getLogger(DefectosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(DefectosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int consultarNumPiezasPorDefecto(String defecto) {
        String response = "";
        try {
            response = rp.call("consultarNumPiezasPorDefecto", defecto);
        } catch (Exception ex) {
            Logger.getLogger(DefectosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.parseInt(response);
    }

    public double consultarCostosDefectos(String defecto) {
        String response = "";
        try {
            response = rp.call("consultarCostosDefectos", defecto);
        } catch (Exception ex) {
            Logger.getLogger(DefectosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Double.parseDouble(response);
    }

    public List<String> consultarDetallePiezas(String defecto) {
        String response = "";
        System.out.println("metodo llamado");
        try {
            response = rp.call("consultarDetallePiezas", defecto);
        } catch (Exception ex) {
            Logger.getLogger(DefectosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Gson gson = new Gson();
        List<String> detalles = gson.fromJson(response, List.class);
        return detalles;
    }
}
