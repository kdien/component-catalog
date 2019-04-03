/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dylan
 */
@Entity
@Table(name = "CODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Code.findAll", query = "SELECT c FROM Code c")
    , @NamedQuery(name = "Code.findByCodeid", query = "SELECT c FROM Code c WHERE c.codeid = :codeid")
    , @NamedQuery(name = "Code.findByCodename", query = "SELECT c FROM Code c WHERE c.codename = :codename")
    , @NamedQuery(name = "Code.findByCodedescription", query = "SELECT c FROM Code c WHERE c.codedescription = :codedescription")
    , @NamedQuery(name = "Code.findByCodeentry", query = "SELECT c FROM Code c WHERE c.codeentry = :codeentry")})
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODEID")
    private Integer codeid;
    @Basic(optional = false)
    @Column(name = "CODENAME")
    private String codename;
    @Basic(optional = false)
    @Column(name = "CODEDESCRIPTION")
    private String codedescription;
    @Basic(optional = false)
    @Column(name = "CODEENTRY")
    private String codeentry;
    @JoinColumn(name = "CATEGORY", referencedColumnName = "CATEGORYID")
    @ManyToOne(optional = false)
    private Category category;

    public Code() {
    }

    public Code(Integer codeid) {
        this.codeid = codeid;
    }

    public Code(Integer codeid, String codename, String codedescription, String codeentry) {
        this.codeid = codeid;
        this.codename = codename;
        this.codedescription = codedescription;
        this.codeentry = codeentry;
    }

    public Integer getCodeid() {
        return codeid;
    }

    public void setCodeid(Integer codeid) {
        this.codeid = codeid;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getCodedescription() {
        return codedescription;
    }

    public void setCodedescription(String codedescription) {
        this.codedescription = codedescription;
    }

    public String getCodeentry() {
        return codeentry;
    }

    public void setCodeentry(String codeentry) {
        this.codeentry = codeentry;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeid != null ? codeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Code)) {
            return false;
        }
        Code other = (Code) object;
        if ((this.codeid == null && other.codeid != null) || (this.codeid != null && !this.codeid.equals(other.codeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Code[ codeid=" + codeid + " ]";
    }
    
}
