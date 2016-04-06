package com.capgemini.hackaton2016.web;

import com.capgemini.hackaton2016.model.AnalyticsPression;
import com.capgemini.hackaton2016.services.AnalyticsService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author afbustamante
 */
@Path("/analytics")
public class AnalyticsRS {

    @Inject
    private AnalyticsService analyticsService;

    /**
     * Creates a new instance of AnalyticsService
     */
    public AnalyticsRS() {
    }

    @GET
    @Path("/pression")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnalyticsPression> getPression() {
        return analyticsService.recupererDonneesPression();
    }
}
