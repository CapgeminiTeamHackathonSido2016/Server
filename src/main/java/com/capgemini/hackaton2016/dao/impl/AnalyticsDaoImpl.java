package com.capgemini.hackaton2016.dao.impl;

import com.capgemini.hackaton2016.dao.AnalyticsDao;
import com.capgemini.hackaton2016.model.AnalyticsPression;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 *
 * @author afbustamante
 */
@Named(value = "analyticsDao")
@RequestScoped
public class AnalyticsDaoImpl implements AnalyticsDao {
    
    public AnalyticsDaoImpl() {}
    
    @Override
    public List<AnalyticsPression> getMessagesAnalytics() {
        try {
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
            
            List<AnalyticsPression> ap = (List<AnalyticsPression>) em.createQuery(
                "select message.id_message AS idMessage, "
                + "message.pression AS pression, "
                + "message.latitude AS latitude, "
                + "message.longitude AS longitude, "
                + "message.id_trajet AS idTrajet, "
                + "trajet.id_camion AS idCamion"
                + "from message "
                + "join trajet "
                + "on message.id_trajet = trajet.id_trajet").getResultList();
            
            utx.commit();
            
            return ap;
        } catch (Exception e) {
            return new ArrayList<>(0);
        }
    }
}
