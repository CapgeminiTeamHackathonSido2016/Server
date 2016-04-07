package com.capgemini.hackaton2016.web;

import com.capgemini.hackaton2016.services.ReceptionMessageService;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    private static final int FACTEUR_PRESSION = 10;
    private static final int FACTEUR_COORDONNEE = 1000000;

    private final Log log = LogFactory.getLog("Web");

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
        log.info("==> Nouveau message : data=" + data + ", device=" + device + " time=" + time);

        BigDecimal[] donnees = decouperDonnees(data);

        if (donnees != null) {
            log.info("Latitude = " + donnees[0].toString());
            log.info("Longitude = " + donnees[1].toString());
            log.info("Pression 1 = " + donnees[2].toString());
            log.info("Pression 2 = " + donnees[3].toString());
            log.info("Pression 3 = " + donnees[4].toString());
            log.info("Pression 4 = " + donnees[5].toString());

            receptionMessageService.enregistrerMessage(device, new Date(time * 1000), donnees[0], donnees[1],
                    new BigDecimal[]{donnees[2], donnees[3], donnees[4], donnees[5]});
        }
        return true;
    }
    
    private BigDecimal[] decouperDonnees(String data) {
        try {
            byte[] donnees = Hex.decodeHex(data.toCharArray());

            byte[] bLatitude = new byte[]{donnees[0], donnees[1], donnees[2], donnees[3]};
            byte[] bLongitude = new byte[]{donnees[4], donnees[5], donnees[6], donnees[7]};
            byte[] bPression1 = new byte[]{donnees[8]};
            byte[] bPression2 = new byte[]{donnees[9]};
            byte[] bPression3 = new byte[]{donnees[10]};
            byte[] bPression4 = new byte[]{donnees[11]};

            BigDecimal latitude = BigDecimal.valueOf(byteToDouble(bLatitude, 4) / FACTEUR_COORDONNEE).setScale(7, BigDecimal.ROUND_DOWN);
            BigDecimal longitude = BigDecimal.valueOf(byteToDouble(bLongitude, 4) / FACTEUR_COORDONNEE).setScale(7, BigDecimal.ROUND_DOWN);
            BigDecimal pression1 = BigDecimal.valueOf(byteToDouble(bPression1, 1) / FACTEUR_PRESSION).setScale(2, BigDecimal.ROUND_DOWN);
            BigDecimal pression2 = BigDecimal.valueOf(byteToDouble(bPression2, 1) / FACTEUR_PRESSION).setScale(2, BigDecimal.ROUND_DOWN);
            BigDecimal pression3 = BigDecimal.valueOf(byteToDouble(bPression3, 1) / FACTEUR_PRESSION).setScale(2, BigDecimal.ROUND_DOWN);
            BigDecimal pression4 = BigDecimal.valueOf(byteToDouble(bPression4, 1) / FACTEUR_PRESSION).setScale(2, BigDecimal.ROUND_DOWN);

            BigDecimal[] resultat = new BigDecimal[6];
            resultat[0] = latitude;
            resultat[1] = longitude;
            resultat[2] = pression1;
            resultat[3] = pression2;
            resultat[4] = pression3;
            resultat[5] = pression4;
            return resultat;
        } catch (DecoderException e) {
            log.error("Erreur de decodage", e);
            return null;
        }
    }

    private double byteToDouble(byte[] bytes, int length) {
        int val = 0;

        for (int i = 0; i < length; i++) {
            val = val << 8;
            val = val |(bytes[i] & 0xFF);
        }
        return (double) val;
    }
}