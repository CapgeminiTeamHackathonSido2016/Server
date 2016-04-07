package com.capgemini.hackaton2016.web;

import com.capgemini.hackaton2016.services.AnalyticsService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author afbustamante
 */
@Path("/camions")
@RequestScoped
public class LocalisationCamionRS {

    @Inject
    private AnalyticsService analyticsService;

    /**
     * Creates a new instance of LocalisationCamionRS
     */
    public LocalisationCamionRS() {
    }

    /**
     * Retrieves representation of an instance of com.capgemini.hackaton2016.web.LocalisationCamionRS
     * @param idCamion
     * @return an instance of String
     */
    @GET
    @Path("/localisation/{idCamion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getXml(@PathParam(value = "idCamion") Short idCamion) {
        Map<String, String> localisation = new HashMap<>();

        BigDecimal[] coordonnees = analyticsService.localiserCamion(idCamion);

        if (coordonnees != null) {
            localisation.put("latitude", coordonnees[0].toString());
            localisation.put("longitude", coordonnees[1].toString());
        }
        return localisation;
    }
}
