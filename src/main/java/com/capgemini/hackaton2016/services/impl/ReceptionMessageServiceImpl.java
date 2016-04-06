package com.capgemini.hackaton2016.services.impl;

import com.capgemini.hackaton2016.dao.MessageDao;
import com.capgemini.hackaton2016.model.Message;
import com.capgemini.hackaton2016.services.ReceptionMessageService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author afbustamante
 */
@RequestScoped
public class ReceptionMessageServiceImpl implements ReceptionMessageService {
    
    @Inject
    MessageDao messageDao;
    
    private final Log log = LogFactory.getLog("Services");

    @Override
    public void enregistrerMessage(String idAppareil, Date date, 
            BigDecimal latitude, BigDecimal longitude, BigDecimal pression) {
        
        try {
            Message msg = messageDao.recupererInfoMessage(idAppareil, date);
            msg.setLatitude(latitude);
            msg.setLongitude(longitude);
            msg.setPression(pression);
            msg.setDateCreation(date);
            
            messageDao.insererMessage(msg);
        } catch (SQLException e) {
            log.error("Impossible de faire l'insertion", e);
        }
    }
    
}
