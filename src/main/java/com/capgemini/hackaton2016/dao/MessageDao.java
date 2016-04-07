package com.capgemini.hackaton2016.dao;

import com.capgemini.hackaton2016.model.Message;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author afbustamante
 */
public interface MessageDao {
    
    Message recupererInfoMessage(String idAppareil, int positionPneu, Date date)
            throws SQLException;
    
    void insererMessage(Message msg) throws SQLException;
}
