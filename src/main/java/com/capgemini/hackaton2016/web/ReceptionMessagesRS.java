package com.capgemini.hackaton2016.web;

import com.capgemini.hackaton2016.services.ReceptionMessageService;
import java.math.BigDecimal;
import java.util.Date;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author afbustamante
 */
@Path("/messages")
@RequestScoped
@Produces(MediaType.TEXT_PLAIN)
public class ReceptionMessagesRS {
    
    @Inject
    private ReceptionMessageService receptionMessageService;

    /**
     * Creates a new instance of ReceptionMessagesRS
     */
    public ReceptionMessagesRS() {
    }

    /**
     * Retrieves representation of an instance of com.capgemini.hackaton2016.web.ReceptionMessagesRS
     * @param device
     * @param time
     * @param data
     * @param rssi
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/enregistrer")
    public Boolean enregistrerMessage(@QueryParam("device") String device, 
            @QueryParam("time") Long time, @QueryParam("data") String data, 
            @QueryParam("rssi") String rssi) {
        receptionMessageService.enregistrerMessage(device, new Date(time * 1000L), 
                BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
        
        return true;
    }
}
