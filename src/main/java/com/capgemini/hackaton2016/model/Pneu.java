package com.capgemini.hackaton2016.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "pneu", catalog = "hackaton", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pneu.findAll", query = "SELECT p FROM Pneu p")})
public class Pneu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pneu")
    private Short idPneu;
    @Size(max = 100)
    @Column(name = "marque")
    private String marque;
    @Column(name = "position")
    private Short position;
    @Column(name = "essieu")
    private Short essieu;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pression_normale")
    private BigDecimal pressionNormale;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPneu")
    private List<Message> messageList;
    @JoinColumn(name = "id_camion", referencedColumnName = "id_camion")
    @ManyToOne
    private Camion idCamion;

    public Pneu() {
    }

    public Pneu(Short idPneu) {
        this.idPneu = idPneu;
    }

    public Short getIdPneu() {
        return idPneu;
    }

    public void setIdPneu(Short idPneu) {
        this.idPneu = idPneu;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Short getPosition() {
        return position;
    }

    public void setPosition(Short position) {
        this.position = position;
    }

    public Short getEssieu() {
        return essieu;
    }

    public void setEssieu(Short essieu) {
        this.essieu = essieu;
    }

    public BigDecimal getPressionNormale() {
        return pressionNormale;
    }

    public void setPressionNormale(BigDecimal pressionNormale) {
        this.pressionNormale = pressionNormale;
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
        hash += (idPneu != null ? idPneu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pneu)) {
            return false;
        }
        Pneu other = (Pneu) object;
        if ((this.idPneu == null && other.idPneu != null) || (this.idPneu != null && !this.idPneu.equals(other.idPneu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capgemini.hackaton2016.model.Pneu[ idPneu=" + idPneu + " ]";
    }
    
}
