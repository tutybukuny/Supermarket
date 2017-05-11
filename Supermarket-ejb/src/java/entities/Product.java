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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "product", catalog = "supermarket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByType", query = "SELECT p FROM Product p WHERE p.productTypeID = :type"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByCost", query = "SELECT p FROM Product p WHERE p.cost = :cost")})
public class Product implements Serializable {
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Cost", precision = 12)
    private Float cost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private List<Saleproduct> saleproductList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private List<Selectedproduct> selectedproductList;
    @JoinColumn(name = "ProductTypeID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Producttype productTypeID;
    @JoinColumn(name = "ProductSetID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Productset productSetID;
    @JoinColumn(name = "ManufacturerID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Manufacturer manufacturerID;
    @JoinColumn(name = "PreviewID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Preview previewID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private List<Topproduct> topproductList;

    public Product() {
    }

    public Product(Integer id) {
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

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @XmlTransient
    public List<Saleproduct> getSaleproductList() {
        return saleproductList;
    }

    public void setSaleproductList(List<Saleproduct> saleproductList) {
        this.saleproductList = saleproductList;
    }

    @XmlTransient
    public List<Selectedproduct> getSelectedproductList() {
        return selectedproductList;
    }

    public void setSelectedproductList(List<Selectedproduct> selectedproductList) {
        this.selectedproductList = selectedproductList;
    }

    public Producttype getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(Producttype productTypeID) {
        this.productTypeID = productTypeID;
    }

    public Productset getProductSetID() {
        return productSetID;
    }

    public void setProductSetID(Productset productSetID) {
        this.productSetID = productSetID;
    }

    public Manufacturer getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(Manufacturer manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public Preview getPreviewID() {
        return previewID;
    }

    public void setPreviewID(Preview previewID) {
        this.previewID = previewID;
    }

    @XmlTransient
    public List<Topproduct> getTopproductList() {
        return topproductList;
    }

    public void setTopproductList(List<Topproduct> topproductList) {
        this.topproductList = topproductList;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Product[ id=" + id + " ]";
    }
}
