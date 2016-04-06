package com.capgemini.hackaton2016.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author afbustamante
 */
@Entity
@Table(name = "trajet", catalog = "hackaton", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trajet.findAll", query = "SELECT t FROM Trajet t")})
public class Trajet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_trajet")
    private Short idTrajet;
    @Size(max = 100)
    @Column(name = "nom")
    private String nom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude_depart")
    private BigDecimal latitudeDepart;
    @Column(name = "longitude_depart")
    private BigDecimal longitudeDepart;
    @Column(name = "latitude_arrivee")
    private BigDecimal latitudeArrivee;
    @Column(name = "longitude_arrivee")
    private BigDecimal longitudeArrivee;
    @OneToMany(mappedBy = "trajet")
    private List<Message> messageList;
    @JoinColumn(name = "id_camion", referencedColumnName = "id_camion")
    @ManyToOne(optional = false)
    private Camion idCamion;

    public Trajet() {
    }

    public Trajet(Short idTrajet) {
        this.idTrajet = idTrajet;
    }

    public Short getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Short idTrajet) {
        this.idTrajet = idTrajet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getLatitudeDepart() {
        return latitudeDepart;
    }

    public void setLatitudeDepart(BigDecimal latitudeDepart) {
        this.latitudeDepart = latitudeDepart;
    }

    public BigDecimal getLongitudeDepart() {
        return longitudeDepart;
    }

    public void setLongitudeDepart(BigDecimal longitudeDepart) {
        this.longitudeDepart = longitudeDepart;
    }

    public BigDecimal getLatitudeArrivee() {
        return latitudeArrivee;
    }

    public void setLatitudeArrivee(BigDecimal latitudeArrivee) {
        this.latitudeArrivee = latitudeArrivee;
    }

    public BigDecimal getLongitudeArrivee() {
        return longitudeArrivee;
    }

    public void setLongitudeArrivee(BigDecimal longitudeArrivee) {
        this.longitudeArrivee = longitudeArrivee;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Camion getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Camion idCamion) {
        this.idCamion = idCamion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrajet != null ? idTrajet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trajet)) {
            return false;
        }
        Trajet other = (Trajet) object;
        if ((this.idTrajet == null && other.idTrajet != null) || (this.idTrajet != null && !this.idTrajet.equals(other.idTrajet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capgemini.hackaton2016.model.Trajet[ idTrajet=" + idTrajet + " ]";
    }
    
}
