package com.capgemini.hackaton2016.services.impl;

import com.capgemini.hackaton2016.dao.AnalyticsDao;
import com.capgemini.hackaton2016.model.AnalyticsPression;
import com.capgemini.hackaton2016.services.AnalyticsService;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;

/**
 *
 * @author afbustamante
 */
@Named(value = "analyticsService")
@RequestScoped
public class AnalyticsServiceImpl implements AnalyticsService {
    
    @Inject
    private AnalyticsDao analyticsDao;
    
//    @Resource
//    UserTransaction utx;

    @Override
    public List<AnalyticsPression> recupererDonneesPression() {
        return analyticsDao.getMessagesAnalytics();
    }
    
}
