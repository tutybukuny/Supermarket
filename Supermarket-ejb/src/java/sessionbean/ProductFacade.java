/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Human;
import entities.Product;
import entities.Producttype;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TrungNguyen
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {
    @PersistenceContext(unitName = "Supermarket-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public List<Product> findByType(int productType) {
        List<Product> list = null;
        Producttype producttype = new Producttype(new Integer(productType));
        Query query = getEntityManager().createNamedQuery(Product.class.getSimpleName() + ".findByType", Product.class);
        query.setParameter("type", producttype);
        
        try{
            list = query.getResultList();
        }catch(Exception e){
            return list;
        }
       return list;
    }
}
