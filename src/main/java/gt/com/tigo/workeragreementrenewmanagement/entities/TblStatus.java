/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.tigo.workeragreementrenewmanagement.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author paulo.martinez@ingeneo.com.co
 */
@Entity
@Table(name = "TBL_STATUS", catalog = "", schema = "WORKERAGREEMENT")
@NamedQueries({
    @NamedQuery(name = "TblStatus.findAll", query = "SELECT t FROM TblStatus t")})
public class TblStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_STATUS")
    private Integer idStatus;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ID_STATUS_TYPE", referencedColumnName = "ID_STATUS_TYPE")
    @ManyToOne(optional = false)
    private TblStatusType idStatusType;

    public TblStatus() {
    }

    public TblStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public TblStatus(Integer idStatus, String name) {
        this.idStatus = idStatus;
        this.name = name;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TblStatusType getIdStatusType() {
        return idStatusType;
    }

    public void setIdStatusType(TblStatusType idStatusType) {
        this.idStatusType = idStatusType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatus != null ? idStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblStatus)) {
            return false;
        }
        TblStatus other = (TblStatus) object;
        if ((this.idStatus == null && other.idStatus != null) || (this.idStatus != null && !this.idStatus.equals(other.idStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "contadorlineacodigo.TblStatus[ idStatus=" + idStatus + " ]";
    }
    
}
