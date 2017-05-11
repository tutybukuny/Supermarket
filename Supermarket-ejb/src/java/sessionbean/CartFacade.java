/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Cart;
import entities.Product;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TrungNguyen
 */
@Stateless
public class CartFacade extends AbstractFacade<Cart> implements CartFacadeLocal {

    @PersistenceContext(unitName = "Supermarket-ejbPU")
    private EntityManager em;
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartFacade() {
        super(Cart.class);
    }

    
}
