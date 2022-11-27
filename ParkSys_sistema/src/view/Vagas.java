/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Diogo
 */
@Entity
@Table(name = "vagas", catalog = "parksys", schema = "")
@NamedQueries({
    @NamedQuery(name = "Vagas.findAll", query = "SELECT v FROM Vagas v"),
    @NamedQuery(name = "Vagas.findByCupom", query = "SELECT v FROM Vagas v WHERE v.cupom = :cupom"),
    @NamedQuery(name = "Vagas.findByPlaca", query = "SELECT v FROM Vagas v WHERE v.placa = :placa"),
    @NamedQuery(name = "Vagas.findByObserva\u00e7\u00e3o", query = "SELECT v FROM Vagas v WHERE v.observa\u00e7\u00e3o = :observa\u00e7\u00e3o"),
    @NamedQuery(name = "Vagas.findByHrEntrada", query = "SELECT v FROM Vagas v WHERE v.hrEntrada = :hrEntrada"),
    @NamedQuery(name = "Vagas.findByHrSaida", query = "SELECT v FROM Vagas v WHERE v.hrSaida = :hrSaida")})
public class Vagas implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cupom")
    private Integer cupom;
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    @Basic(optional = false)
    @Column(name = "observa\u00e7\u00e3o")
    private String observação;
    @Column(name = "hr_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hrEntrada;
    @Column(name = "hr_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hrSaida;

    public Vagas() {
    }

    public Vagas(Integer cupom) {
        this.cupom = cupom;
    }

    public Vagas(Integer cupom, String placa, String observação) {
        this.cupom = cupom;
        this.placa = placa;
        this.observação = observação;
    }

    public Integer getCupom() {
        return cupom;
    }

    public void setCupom(Integer cupom) {
        Integer oldCupom = this.cupom;
        this.cupom = cupom;
        changeSupport.firePropertyChange("cupom", oldCupom, cupom);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        String oldPlaca = this.placa;
        this.placa = placa;
        changeSupport.firePropertyChange("placa", oldPlaca, placa);
    }

    public String getObservação() {
        return observação;
    }

    public void setObservação(String observação) {
        String oldObservação = this.observação;
        this.observação = observação;
        changeSupport.firePropertyChange("observa\u00e7\u00e3o", oldObservação, observação);
    }

    public Date getHrEntrada() {
        return hrEntrada;
    }

    public void setHrEntrada(Date hrEntrada) {
        Date oldHrEntrada = this.hrEntrada;
        this.hrEntrada = hrEntrada;
        changeSupport.firePropertyChange("hrEntrada", oldHrEntrada, hrEntrada);
    }

    public Date getHrSaida() {
        return hrSaida;
    }

    public void setHrSaida(Date hrSaida) {
        Date oldHrSaida = this.hrSaida;
        this.hrSaida = hrSaida;
        changeSupport.firePropertyChange("hrSaida", oldHrSaida, hrSaida);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cupom != null ? cupom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vagas)) {
            return false;
        }
        Vagas other = (Vagas) object;
        if ((this.cupom == null && other.cupom != null) || (this.cupom != null && !this.cupom.equals(other.cupom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "view.Vagas[ cupom=" + cupom + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
