/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TrungNguyen
 */
@Entity
@Table(name = "producttype", catalog = "supermarket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producttype.findAll", query = "SELECT p FROM Producttype p"),
    @NamedQuery(name = "Producttype.findById", query = "SELECT p FROM Producttype p WHERE p.id = :id"),
    @NamedQuery(name = "Producttype.findByName", query = "SELECT p FROM Producttype p WHERE p.name = :name"),
    @NamedQuery(name = "Producttype.findByDescription", query = "SELECT p FROM Producttype p WHERE p.description = :description")})
public class Producttype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "Name", length = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "Description", length = 255)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productTypeID")
    private List<Product> productList;

    public Producttype() {
    }

    public Producttype(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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
        if (!(object instanceof Producttype)) {
            return false;
        }
        Producttype other = (Producttype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Producttype[ id=" + id + " ]";
    }
    
}
