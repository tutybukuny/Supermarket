/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TrungNguyen
 */
@Entity
@Table(name = "saleproduct", catalog = "supermarket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Saleproduct.findAll", query = "SELECT s FROM Saleproduct s"),
    @NamedQuery(name = "Saleproduct.findById", query = "SELECT s FROM Saleproduct s WHERE s.id = :id"),
    @NamedQuery(name = "Saleproduct.findByPercentSale", query = "SELECT s FROM Saleproduct s WHERE s.percentSale = :percentSale"),
    @NamedQuery(name = "Saleproduct.findByDateStart", query = "SELECT s FROM Saleproduct s WHERE s.dateStart = :dateStart"),
    @NamedQuery(name = "Saleproduct.findByDateEnd", query = "SELECT s FROM Saleproduct s WHERE s.dateEnd = :dateEnd"),
    @NamedQuery(name = "Saleproduct.findByList", query = "SELECT s FROM Saleproduct s WHERE s.list = :list")})
public class Saleproduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PercentSale", nullable = false)
    private int percentSale;
    @Column(name = "DateStart")
    @Temporal(TemporalType.DATE)
    private Date dateStart;
    @Column(name = "DateEnd")
    @Temporal(TemporalType.DATE)
    private Date dateEnd;
    @Column(name = "List")
    private Integer list;
    @JoinColumn(name = "ProductID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Product productID;

    public Saleproduct() {
    }

    public Saleproduct(Integer id) {
        this.id = id;
    }

    public Saleproduct(Integer id, int percentSale) {
        this.id = id;
        this.percentSale = percentSale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPercentSale() {
        return percentSale;
    }

    public void setPercentSale(int percentSale) {
        this.percentSale = percentSale;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getList() {
        return list;
    }

    public void setList(Integer list) {
        this.list = list;
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
        if (!(object instanceof Saleproduct)) {
            return false;
        }
        Saleproduct other = (Saleproduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Saleproduct[ id=" + id + " ]";
    }
    
}
