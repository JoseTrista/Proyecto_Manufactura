/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.serviciojwt;

/**
 *
 * @author jctri
 */
public interface IServicioJWT {
    public String crearToken(String usr);
    
    public void verificarToken(String token);
}
