/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Order1;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TrungNguyen
 */
@Stateless
public class Order1Facade extends AbstractFacade<Order1> implements Order1FacadeLocal {
    @PersistenceContext(unitName = "Supermarket-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Order1Facade() {
        super(Order1.class);
    }
    
}
