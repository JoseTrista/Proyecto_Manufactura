/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.gestorreporte;

import com.mycompany.rabbitmq.DefectosDAO;
import com.mycompany.rabbitmq.RabbitPublisher;
import com.mycompany.rabbitmq.ReporteDTO;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * REST Web Service
 *
 * @author Elkur
 */
@Path("reportes")
@RequestScoped
public class ReportesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReportesResource
     */
    public ReportesResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.gestorreporte.ReportesResource
     *
     * @param defecto
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{defecto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumDefectos(@PathParam("defecto") String defecto) {
        DefectosDAO defDao = new DefectosDAO();
        int numPiezas = defDao.consultarNumPiezasPorDefecto(defecto);
        double costosPerdidas = defDao.consultarCostosDefectos(defecto);
        List<String> detalles = defDao.consultarDetallePiezas(defecto);

        ReporteDTO reporte = new ReporteDTO(defecto, numPiezas, costosPerdidas, detalles);

        System.out.println(reporte);
        return Response.status(Response.Status.CREATED).entity(reporte).build();
    }

    /**
     * PUT method for updating or creating an instance of ReportesResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
