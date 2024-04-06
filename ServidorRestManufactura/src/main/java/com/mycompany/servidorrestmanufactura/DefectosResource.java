/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.servidorrestmanufactura;

import Interfaces.IDefecto;
import com.google.gson.Gson;

import com.mycompany.dao_manufactura.DaoDefecto;
//import com.mycompany.dominiomanufactura.Defecto;
import com.mycompany.dominiprueba.Defecto;
import com.mycompany.serviciogestordefecto.RabbitMQPublisher;
//import com.mycompany.serviciogestordefecto.RabbitMQPublisher;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;

/**
 * REST Web Service
 *
 * @author jctri
 */
@Path("defectos")
@RequestScoped
public class DefectosResource {

    @Context
    private UriInfo context;
    private IDefecto defectoDAO;
    private RabbitMQPublisher rabbitMQPublisher; // Asume inicialización o inyección adecuada
    private Gson gson = new Gson();
    

    /**
     * Creates a new instance of DefectosResource
     */
    public DefectosResource() {
        defectoDAO = new DaoDefecto("localhost", "3306", "manufactura", "root", "Movagro123.,");
        rabbitMQPublisher = new RabbitMQPublisher("localhost", "queue_name");
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String obtenerDefectos() {
//        try {
//            List<Defecto> defectos = em.createQuery("SELECT d FROM Defecto d", Defecto.class).getResultList();
//            return gson.toJson(defectos);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error al obtener los defectos";
//        }
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarDefecto(Defecto defecto) {
//        try {
//            Defecto defecto = gson.fromJson(content, Defecto.class);
//            Defecto aux = defectoDAO.agregarDefecto(defecto);
//            String mensajeJson = gson.toJson(defecto);
//            rabbitMQPublisher.publish(mensajeJson);
//            return gson.toJson(defecto);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error al registrar el defecto";
//        }
////        if (defecto != null) {
////            Defecto aux = defectoDAO.agregarDefecto(defecto);
////            return Response.status(Response.Status.CREATED).entity(aux).build();
////        } else {
////            // Aquí, asegúrate de enviar una respuesta con contenido JSON que explique el error
////            String error = "{\"error\":\"Datos de defecto no proporcionados o inválidos.\"}";
////            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(error).build();
////        }
        try {
            
            Defecto aux = defectoDAO.agregarDefecto(defecto);

            String mensajeJson = gson.toJson(aux);
            rabbitMQPublisher.publish(mensajeJson);

            return Response.status(Response.Status.CREATED).entity(aux).build();
        } catch (Exception e) {
            e.printStackTrace();
            String error = "error: Error al procesar la solicitud";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
}
