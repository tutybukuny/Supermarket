/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
 * @author TrungNguyen
 */
@Entity
@Table(name = "topproduct", catalog = "supermarket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topproduct.findAll", query = "SELECT t FROM Topproduct t"),
    @NamedQuery(name = "Topproduct.findById", query = "SELECT t FROM Topproduct t WHERE t.id = :id"),
    @NamedQuery(name = "Topproduct.findByList", query = "SELECT t FROM Topproduct t WHERE t.list = :list"),
    @NamedQuery(name = "Topproduct.findByCriteria", query = "SELECT t FROM Topproduct t WHERE t.criteria = :criteria")})
public class Topproduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "List")
    private Integer list;
    @Column(name = "Criteria")
    private Integer criteria;
    @JoinColumn(name = "CriteriaID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Criteria criteriaID;
    @JoinColumn(name = "ProductID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Product productID;

    public Topproduct() {
    }

    public Topproduct(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getList() {
        return list;
    }

    public void setList(Integer list) {
        this.list = list;
    }

    public Integer getCriteria() {
        return criteria;
    }

    public void setCriteria(Integer criteria) {
        this.criteria = criteria;
    }

    public Criteria getCriteriaID() {
        return criteriaID;
    }

    public void setCriteriaID(Criteria criteriaID) {
        this.criteriaID = criteriaID;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topproduct)) {
            return false;
        }
        Topproduct other = (Topproduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Topproduct[ id=" + id + " ]";
    }
    
}
