package com.capgemini.hackaton2016.web;

import com.capgemini.hackaton2016.services.AnalyticsService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.math.BigDecimal;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author afbustamante
 */
@Named(value = "carteBean")
@ManagedBean
@RequestScoped
public class CarteBean {
    
    @Inject
    private AnalyticsService analyticsService;
    
    private String latitude;
    private String longitude;

    /**
     * Creates a new instance of CarteBean
     */
    public CarteBean() {
    }

    @PostConstruct
    public void trouverPosition() {
        BigDecimal[] position = analyticsService.localiserCamion(new Short("1"));
        latitude = position[0].toString();
        longitude = position[1].toString();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
