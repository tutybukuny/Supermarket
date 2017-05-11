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
@Table(name = "selectedproduct", catalog = "supermarket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Selectedproduct.findAll", query = "SELECT s FROM Selectedproduct s"),
    @NamedQuery(name = "Selectedproduct.findById", query = "SELECT s FROM Selectedproduct s WHERE s.id = :id")})
public class Selectedproduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @JoinColumn(name = "ProductID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Product productID;
    @JoinColumn(name = "CartID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Cart cartID;

    public Selectedproduct() {
    }

    public Selectedproduct(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public Cart getCartID() {
        return cartID;
    }

    public void setCartID(Cart cartID) {
        this.cartID = cartID;
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
        if (!(object instanceof Selectedproduct)) {
            return false;
        }
        Selectedproduct other = (Selectedproduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Selectedproduct[ id=" + id + " ]";
    }
    
}
