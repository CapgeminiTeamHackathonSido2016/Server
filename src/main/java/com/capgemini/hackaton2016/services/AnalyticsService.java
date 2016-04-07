package com.capgemini.hackaton2016.services;

import com.capgemini.hackaton2016.model.AnalyticsPression;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author afbustamante
 */
public interface AnalyticsService {
    
    List<AnalyticsPression> recupererDonneesPression();

    BigDecimal[] localiserCamion(Short idCamion);
}
