package com.capgemini.hackaton2016.dao.impl;

import com.capgemini.hackaton2016.dao.MessageDao;
import com.capgemini.hackaton2016.model.Message;
import com.capgemini.hackaton2016.model.Pneu;
import com.capgemini.hackaton2016.model.Trajet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author afbustamante
 */
@RequestScoped
public class MessageDaoImpl implements MessageDao {
    
    private static final Short ID_TRAJET_INCONNU = 0;
    
    private final Log log = LogFactory.getLog("DAO");
    
    @Resource(lookup = "java:/jdbc/hackaton")
    private DataSource ds;

    @Override
    public Message recupererInfoMessage(String idAppareil, Date date) 
            throws SQLException {
        Message msg = null;
        Connection cnx = null;
        PreparedStatement ps = null;

        try {
            cnx = ds.getConnection();
            cnx.setAutoCommit(false);
            ps = cnx.prepareStatement(
                    "select id_pneu from pneu where id_appareil like ?"
            );
            ps.setString(1, idAppareil);
            ps.setMaxRows(1);

            ResultSet rs = ps.executeQuery();
            
            if (rs != null && rs.next()) {
                msg = new Message();
                msg.setPneu(new Pneu(rs.getShort(1)));
                
                ps = cnx.prepareStatement(
                        "select t.id_trajet " +
                        "from trajet t " +
                        "join camion c on c.id_camion = t.id_camion " +
                        "join pneu p on c.id_camion = p.id_camion " +
                        "where ? between t.date_depart and t.date_arrivee"
                );
                ps.setDate(1, new java.sql.Date(date.getTime()));
                ps.setMaxRows(1);
                rs = ps.executeQuery();
                
                if (rs != null && rs.next()) {
                    msg.setTrajet(new Trajet(rs.getShort(1)));
                }
            }
            cnx.commit();
            return msg;
        } catch (Exception e) {
            log.error("Erreur de BDD", e);
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

    @Override
    public void insererMessage(Message msg) throws SQLException {
        Connection cnx = null;
        PreparedStatement ps = null;

        try {
            cnx = ds.getConnection();
            ps = cnx.prepareStatement(
                    "insert into message " +
                    "(id_message, id_pneu, id_trajet, latitude, longitude, pression, date_creation) " +
                    "values " +
                    "(nextval('s_message'), ?, ?, ?, ?, ?, ?);"
            );
            ps.setShort(1, msg.getPneu().getIdPneu());
            
            if (msg.getTrajet() != null) {
                ps.setShort(2, msg.getTrajet().getIdTrajet());
            } else {
                ps.setShort(2, ID_TRAJET_INCONNU);
            }
            
            ps.setBigDecimal(3, msg.getLatitude());
            ps.setBigDecimal(4, msg.getLongitude());
            ps.setBigDecimal(5, msg.getPression());
            ps.setDate(6, new java.sql.Date(msg.getDateCreation().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            log.error("Erreur de BDD", e);
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
