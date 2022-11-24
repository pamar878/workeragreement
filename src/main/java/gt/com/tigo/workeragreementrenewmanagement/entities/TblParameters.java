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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author paulo.martinez@ingeneo.com.co
 */
@Entity
@Table(name = "TBL_PARAMETERS", catalog = "", schema = "WORKERAGREEMENT")
@NamedQueries({
    @NamedQuery(name = "TblParameters.findAll", query = "SELECT t FROM TblParameters t")})
public class TblParameters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PARAMETER")
    private Integer idParameter;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "VALUE")
    private String value;
    @Column(name = "DESCRIPTION")
    private String description;

    public TblParameters() {
    }

    public TblParameters(Integer idParameter) {
        this.idParameter = idParameter;
    }

    public TblParameters(Integer idParameter, String name, String value) {
        this.idParameter = idParameter;
        this.name = name;
        this.value = value;
    }

    public Integer getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(Integer idParameter) {
        this.idParameter = idParameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParameter != null ? idParameter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblParameters)) {
            return false;
        }
        TblParameters other = (TblParameters) object;
        if ((this.idParameter == null && other.idParameter != null) || (this.idParameter != null && !this.idParameter.equals(other.idParameter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "contadorlineacodigo.TblParameters[ idParameter=" + idParameter + " ]";
    }
    
}
