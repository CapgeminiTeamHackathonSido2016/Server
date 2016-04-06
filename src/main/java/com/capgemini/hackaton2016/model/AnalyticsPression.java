package com.capgemini.hackaton2016.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author afbustamante
 */
public class AnalyticsPression {
    
    private Long idMessage;
    private Integer idCamion;
    private Integer idPneu;
    private Date timestamp;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal pression;
    private boolean alerte;

    public AnalyticsPression() {
    }

    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public Integer getIdPneu() {
        return idPneu;
    }

    public void setIdPneu(Integer idPneu) {
        this.idPneu = idPneu;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getPression() {
        return pression;
    }

    public void setPression(BigDecimal pression) {
        this.pression = pression;
    }

    public boolean isAlerte() {
        return alerte;
    }

    public void setAlerte(boolean alerte) {
        this.alerte = alerte;
    }
}
