package com.capgemini.hackaton2016.model;

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
    private Date latitude;
    private Date longitude;

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

    public Date getLatitude() {
        return latitude;
    }

    public void setLatitude(Date latitude) {
        this.latitude = latitude;
    }

    public Date getLongitude() {
        return longitude;
    }

    public void setLongitude(Date longitude) {
        this.longitude = longitude;
    }
}
