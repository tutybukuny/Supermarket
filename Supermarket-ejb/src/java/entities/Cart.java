/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TrungNguyen
 */
@Entity
@Table(name = "cart", catalog = "supermarket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findById", query = "SELECT c FROM Cart c WHERE c.id = :id"),
    @NamedQuery(name = "Cart.findByTotalCost", query = "SELECT c FROM Cart c WHERE c.totalCost = :totalCost")})

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalCost", nullable = false)
    private float totalCost;
    @JoinColumn(name = "HumanID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Human humanID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartID")
    private List<Selectedproduct> selectedproductList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartID")
    private List<Bill> billList;

    
    public Cart() {
    }

    public Cart(Integer id) {
        this.id = id;
    }

    public Cart(Integer id, float totalCost) {
        this.id = id;
        this.totalCost = totalCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public Human getHumanID() {
        return humanID;
    }

    public void setHumanID(Human humanID) {
        this.humanID = humanID;
    }

    @XmlTransient
    public List<Selectedproduct> getSelectedproductList() {
        return selectedproductList;
    }

    public void setSelectedproductList(List<Selectedproduct> selectedproductList) {
        this.selectedproductList = selectedproductList;
    }

    @XmlTransient
    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
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
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cart[ id=" + id + " ]";
    }
    
}
