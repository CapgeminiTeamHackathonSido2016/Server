package com.capgemini.hackaton2016.services.impl;

import com.capgemini.hackaton2016.dao.AnalyticsDao;
import com.capgemini.hackaton2016.model.AnalyticsPression;
import com.capgemini.hackaton2016.services.AnalyticsService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author afbustamante
 */
@Named(value = "analyticsService")
@RequestScoped
public class AnalyticsServiceImpl implements AnalyticsService {
    
    private final Log log = LogFactory.getLog("Services");
    
    @Inject
    private AnalyticsDao analyticsDao;

    @Override
    public List<AnalyticsPression> recupererDonneesPression() {
        log.info("Recuperation des donnees analytics");
        
        try {
            return analyticsDao.getMessagesAnalytics();
        } catch (SQLException e) {
            log.error("Erreur de BD", e);
            return new ArrayList<>(0);
        }
    }
    
}
