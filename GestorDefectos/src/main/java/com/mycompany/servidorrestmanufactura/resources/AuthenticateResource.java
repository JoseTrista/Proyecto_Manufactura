/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.servidorrestmanufactura.resources;

import Token.Mensaje;
import com.mycompany.serviciojwt.ServicioJWT;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jctri
 */
@Path("authenticate")
@RequestScoped
public class AuthenticateResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AuthenticateResource
     */
    public AuthenticateResource() {
    }

    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Mensaje mensajito) {
        try {
            // Extraer el usuario y la contraseña del payload
            String username = mensajito.getUser(); // Estos deberían ser extraídos del payload
            
            // Aquí deberías verificar las credenciales contra una base de datos o un sistema de autenticación
                ServicioJWT jwtService = new ServicioJWT();
                String token = jwtService.crearToken(username);

                return Response.ok("{\"token\": \"" + token + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la solicitud").build();
        }
    }
    /**
     * Retrieves representation of an instance of com.mycompany.servidorrestmanufactura.resources.AuthenticateResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AuthenticateResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
