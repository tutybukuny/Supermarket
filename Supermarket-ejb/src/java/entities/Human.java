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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TrungNguyen
 */
@Entity
@Table(name = "human", catalog = "supermarket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Human.findAll", query = "SELECT h FROM Human h"),
    @NamedQuery(name = "Human.findById", query = "SELECT h FROM Human h WHERE h.id = :id"),
    @NamedQuery(name = "Human.getHumanByAccount", query = "SELECT h FROM Human h, Account a WHERE a.username = :username AND a.password = :password"),
    @NamedQuery(name = "Human.getHumanByFbID", query = "SELECT h FROM Human h, Account a WHERE a.facebookID = :facebookID"),
    @NamedQuery(name = "Human.findByDiscriminator", query = "SELECT h FROM Human h WHERE h.discriminator = :discriminator")})
public class Human implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Discriminator", nullable = false, length = 255)
    private String discriminator;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "humanID")
    private List<Birthday> birthdayList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "humanID")
    private List<Cart> cartList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "humanID")
    private List<Address> addressList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "humanID")
    private List<Name> nameList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "humanID")
    private List<Account> accountList;
   
    public Human() {
    }

    public Human(Integer id) {
        this.id = id;
    }

    public Human(Integer id, String discriminator) {
        this.id = id;
        this.discriminator = discriminator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    @XmlTransient
    public List<Birthday> getBirthdayList() {
        return birthdayList;
    }

    public void setBirthdayList(List<Birthday> birthdayList) {
        this.birthdayList = birthdayList;
    }

    @XmlTransient
    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @XmlTransient
    public List<Name> getNameList() {
        return nameList;
    }

    public void setNameList(List<Name> nameList) {
        this.nameList = nameList;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
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
        if (!(object instanceof Human)) {
            return false;
        }
        Human other = (Human) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Human[ id=" + id + " ]";
    }
    
}
