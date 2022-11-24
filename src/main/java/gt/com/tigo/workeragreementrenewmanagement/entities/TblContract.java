/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.tigo.workeragreementrenewmanagement.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author paulo.martinez@ingeneo.com.co
 */
@Entity
@Table(name = "TBL_CONTRACT", catalog = "", schema = "WORKERAGREEMENT")
@NamedQueries({
    @NamedQuery(name = "TblContract.findAll", query = "SELECT t FROM TblContract t")})
public class TblContract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONTRACT")
    private Integer idContract;
    @Basic(optional = false)
    @Column(name = "REQUEST_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDatetime;
    @Basic(optional = false)
    @Column(name = "LAST_QUERY_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastQueryDatetime;
    @Basic(optional = false)
    @Column(name = "ID_STATUS")
    private int idStatus;
    @Column(name = "STATUS_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDatetime;
    @Column(name = "ID_HORUS")
    private String idHorus;
    @JoinColumn(name = "ID_PLATFORM", referencedColumnName = "ID_PLATFORM")
    @ManyToOne(optional = false)
    private TblPlatform idPlatform;

    public TblContract() {
    }

    public TblContract(Integer idContract) {
        this.idContract = idContract;
    }

    public TblContract(Integer idContract, Date requestDatetime, Date lastQueryDatetime, int idStatus) {
        this.idContract = idContract;
        this.requestDatetime = requestDatetime;
        this.lastQueryDatetime = lastQueryDatetime;
        this.idStatus = idStatus;
    }

    public Integer getIdContract() {
        return idContract;
    }

    public void setIdContract(Integer idContract) {
        this.idContract = idContract;
    }

    public Date getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(Date requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public Date getLastQueryDatetime() {
        return lastQueryDatetime;
    }

    public void setLastQueryDatetime(Date lastQueryDatetime) {
        this.lastQueryDatetime = lastQueryDatetime;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public Date getStatusDatetime() {
        return statusDatetime;
    }

    public void setStatusDatetime(Date statusDatetime) {
        this.statusDatetime = statusDatetime;
    }

    public TblPlatform getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(TblPlatform idPlatform) {
        this.idPlatform = idPlatform;
    }

    public String getIdHorus() {
		return idHorus;
	}

	public void setIdHorus(String idHorus) {
		this.idHorus = idHorus;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idContract != null ? idContract.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblContract)) {
            return false;
        }
        TblContract other = (TblContract) object;
        if ((this.idContract == null && other.idContract != null) || (this.idContract != null && !this.idContract.equals(other.idContract))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "contadorlineacodigo.TblContract[ idContract=" + idContract + " ]";
    }
    
}
