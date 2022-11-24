/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.tigo.workeragreementrenewmanagement.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author paulo.martinez@ingeneo.com.co
 */
@Entity
@Table(name = "TBL_STATUS_TYPE", catalog = "", schema = "WORKERAGREEMENT")
@NamedQueries({
    @NamedQuery(name = "TblStatusType.findAll", query = "SELECT t FROM TblStatusType t")})
public class TblStatusType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_STATUS_TYPE")
    private Integer idStatusType;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatusType")
    private Collection<TblStatus> tblStatusCollection;

    public TblStatusType() {
    }

    public TblStatusType(Integer idStatusType) {
        this.idStatusType = idStatusType;
    }

    public TblStatusType(Integer idStatusType, String name) {
        this.idStatusType = idStatusType;
        this.name = name;
    }

    public Integer getIdStatusType() {
        return idStatusType;
    }

    public void setIdStatusType(Integer idStatusType) {
        this.idStatusType = idStatusType;
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

    public Collection<TblStatus> getTblStatusCollection() {
        return tblStatusCollection;
    }

    public void setTblStatusCollection(Collection<TblStatus> tblStatusCollection) {
        this.tblStatusCollection = tblStatusCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatusType != null ? idStatusType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblStatusType)) {
            return false;
        }
        TblStatusType other = (TblStatusType) object;
        if ((this.idStatusType == null && other.idStatusType != null) || (this.idStatusType != null && !this.idStatusType.equals(other.idStatusType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "contadorlineacodigo.TblStatusType[ idStatusType=" + idStatusType + " ]";
    }
    
}
