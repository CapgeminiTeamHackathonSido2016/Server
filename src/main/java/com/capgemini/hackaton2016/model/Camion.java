package com.capgemini.hackaton2016.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "camion", catalog = "hackaton", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camion.findAll", query = "SELECT c FROM Camion c")})
public class Camion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_camion")
    private Short idCamion;
    @Size(max = 100)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "idCamion")
    private List<Pneu> pneuList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCamion")
    private List<Trajet> trajetList;

    public Camion() {
    }

    public Camion(Short idCamion) {
        this.idCamion = idCamion;
    }

    public Short getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Short idCamion) {
        this.idCamion = idCamion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Pneu> getPneuList() {
        return pneuList;
    }

    public void setPneuList(List<Pneu> pneuList) {
        this.pneuList = pneuList;
    }

    @XmlTransient
    public List<Trajet> getTrajetList() {
        return trajetList;
    }

    public void setTrajetList(List<Trajet> trajetList) {
        this.trajetList = trajetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCamion != null ? idCamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.idCamion == null && other.idCamion != null) || (this.idCamion != null && !this.idCamion.equals(other.idCamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capgemini.hackaton2016.model.Camion[ idCamion=" + idCamion + " ]";
    }
    
}
