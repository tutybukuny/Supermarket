/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Topproduct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TrungNguyen
 */
@Stateless
public class TopproductFacade extends AbstractFacade<Topproduct> implements TopproductFacadeLocal {
    @PersistenceContext(unitName = "Supermarket-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TopproductFacade() {
        super(Topproduct.class);
    }
    
}
