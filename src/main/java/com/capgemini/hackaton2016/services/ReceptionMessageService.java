package com.capgemini.hackaton2016.services;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author afbustamante
 */
public interface ReceptionMessageService {
    
    void enregistrerMessage(String idAppareil, Date date, BigDecimal latitude, 
            BigDecimal longitude, BigDecimal pression);
}
