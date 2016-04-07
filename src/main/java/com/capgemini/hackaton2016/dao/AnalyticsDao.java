package com.capgemini.hackaton2016.dao;

import com.capgemini.hackaton2016.model.AnalyticsPression;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author afbustamante
 */
public interface AnalyticsDao {
    
    List<AnalyticsPression> getMessagesAnalytics() throws SQLException;

    BigDecimal[] localiserCamion(Short idCamion) throws SQLException;
}
