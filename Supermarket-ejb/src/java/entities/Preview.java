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
@Table(name = "preview", catalog = "supermarket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preview.findAll", query = "SELECT p FROM Preview p"),
    @NamedQuery(name = "Preview.findById", query = "SELECT p FROM Preview p WHERE p.id = :id"),
    @NamedQuery(name = "Preview.findByIntro", query = "SELECT p FROM Preview p WHERE p.intro = :intro"),
    @NamedQuery(name = "Preview.findByImage", query = "SELECT p FROM Preview p WHERE p.image = :image")})
public class Preview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "Intro", length = 255)
    private String intro;
    @Size(max = 255)
    @Column(name = "Image", length = 255)
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "previewID")
    private List<Product> productList;

    public Preview() {
    }

    public Preview(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        if (!(object instanceof Preview)) {
            return false;
        }
        Preview other = (Preview) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Preview[ id=" + id + " ]";
    }
    
}
