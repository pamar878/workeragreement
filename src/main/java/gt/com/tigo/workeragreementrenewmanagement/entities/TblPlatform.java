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
@Table(name = "TBL_PLATFORM", catalog = "", schema = "WORKERAGREEMENT")
@NamedQueries({
    @NamedQuery(name = "TblPlatform.findAll", query = "SELECT t FROM TblPlatform t")})
public class TblPlatform implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PLATFORM")
    private Integer idPlatform;
    @Basic(optional = false)
    @Column(name = "ID_STATUS")
    private int idStatus;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "URL")
    private String url;
    @Column(name = "AUTHORIZATION_TOKEN")
    private String authorizationToken;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlatform")
    private Collection<TblContract> tblContractCollection;

    public TblPlatform() {
    }

    public TblPlatform(Integer idPlatform) {
        this.idPlatform = idPlatform;
    }

    public TblPlatform(Integer idPlatform, int idStatus, String name) {
        this.idPlatform = idPlatform;
        this.idStatus = idStatus;
        this.name = name;
    }

    public Integer getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(Integer idPlatform) {
        this.idPlatform = idPlatform;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public Collection<TblContract> getTblContractCollection() {
        return tblContractCollection;
    }

    public void setTblContractCollection(Collection<TblContract> tblContractCollection) {
        this.tblContractCollection = tblContractCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlatform != null ? idPlatform.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPlatform)) {
            return false;
        }
        TblPlatform other = (TblPlatform) object;
        if ((this.idPlatform == null && other.idPlatform != null) || (this.idPlatform != null && !this.idPlatform.equals(other.idPlatform))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "contadorlineacodigo.TblPlatform[ idPlatform=" + idPlatform + " ]";
    }
    
}
