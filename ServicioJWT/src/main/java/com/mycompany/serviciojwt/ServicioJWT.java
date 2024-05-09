/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.serviciojwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


/**
 *
 * @author jctri
 */
public class ServicioJWT implements IServicioJWT{

    public String crearToken(String usr) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create().withIssuer("auth0").withClaim("usr", usr).sign(algorithm);
        } catch (JWTCreationException exception) {
        }
        return token;
    }
    
    public void verificarToken(String token){
        try{
           Algorithm algorithm = Algorithm.HMAC256("secret");
           JWTVerifier verificador = JWT.require(algorithm).withIssuer("auth0").build();
           
            DecodedJWT jwt = verificador.verify(token);
        }catch (JWTCreationException exception) {
        }
    }
}
