/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.gestorreporte;

import Token.Mensaje;
import com.mycompany.serviciojwt.IServicioJWT;
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
@Path("autenticar")
@RequestScoped
public class AutenticarResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AutenticarResource
     */
    public AutenticarResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.gestorreporte.AutenticarResource
     * @param mensajito
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Mensaje mensajito) {
        try {
            
            String username = mensajito.getUser();
            
                IServicioJWT jwtService = new ServicioJWT();
                String token = jwtService.crearToken(username);

            return Response.ok("{\"token\": \"" + token + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la solicitud").build();
        }
    }

    /**
     * PUT method for updating or creating an instance of AutenticarResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
