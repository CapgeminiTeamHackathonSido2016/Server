package com.capgemini.hackaton2016.dao.impl;

import com.capgemini.hackaton2016.dao.AnalyticsDao;
import com.capgemini.hackaton2016.model.AnalyticsPression;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author afbustamante
 */
@Named(value = "analyticsDao")
@RequestScoped
public class AnalyticsDaoImpl implements AnalyticsDao {

    private static final String ID_CAMION = "idCamion";
    private static final String ID_PNEU = "idPneu";
    private static final String ID_MESSAGE = "idMessage";
    private static final String PRESSION = "pression";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ALERTE = "idCamion";
    private static final String TIMESTAMP = "timestamp";

    private final Log log = LogFactory.getLog("DAO");

    @Resource(lookup = "java:/jdbc/hackaton")
    private DataSource ds;

    public AnalyticsDaoImpl() {
    }

    @Override
    public List<AnalyticsPression> getMessagesAnalytics() throws SQLException {

        Connection cnx = null;
        PreparedStatement ps = null;

        try {
            cnx = ds.getConnection();
            ps = cnx.prepareStatement(
                    "select m.id_message AS idMessage, "
                            + "m.id_pneu AS idPneu, "
                            + "m.pression AS pression, "
                            + "m.latitude AS latitude, "
                            + "m.longitude AS longitude, "
                            + "m.date_creation AS timestamp, "
                            + "m.id_trajet AS idTrajet, "
                            + "m.pression AS pression, "
                            + "p.id_camion AS idCamion, "
                            + "(CASE WHEN p.pression_normale > m.pression THEN 'true' "
                            + "ELSE 'false' END) AS alerte "
                            + "from message m "
                            + "join trajet t on m.id_trajet = t.id_trajet "
                            + "join pneu p on p.id_pneu = m.id_pneu"
            );

            ResultSet rs = ps.executeQuery();
            List<AnalyticsPression> liste = new ArrayList<>();

            if (rs != null) {
                while (rs.next()) {
                    AnalyticsPression a = new AnalyticsPression();
                    a.setIdCamion(rs.getInt(ID_CAMION));
                    a.setIdPneu(rs.getInt(ID_PNEU));
                    a.setIdMessage(rs.getLong(ID_MESSAGE));
                    a.setLatitude(rs.getBigDecimal(LATITUDE));
                    a.setLongitude(rs.getBigDecimal(LONGITUDE));
                    a.setTimestamp(rs.getTimestamp(TIMESTAMP));
                    a.setPression(rs.getBigDecimal(PRESSION));
                    a.setAlerte(rs.getBoolean(ALERTE));

                    liste.add(a);
                }
            }

            return liste;
        } catch (Exception e) {
            log.error("Erreur de BD", e);
            throw new SQLException(e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cnx != null) {
                cnx.close();
            }
        }
    }
}
