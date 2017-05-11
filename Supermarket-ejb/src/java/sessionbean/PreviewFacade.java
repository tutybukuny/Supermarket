/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Preview;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TrungNguyen
 */
@Stateless
public class PreviewFacade extends AbstractFacade<Preview> implements PreviewFacadeLocal {
    @PersistenceContext(unitName = "Supermarket-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreviewFacade() {
        super(Preview.class);
    } 
    
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Preview findByImage(String image) {
        String jpql = "SELECT p FROM Preview p WHERE p.image = :image";
        Query query = em.createQuery(jpql);
        query.setParameter("image", image);
        try{
            Preview preview = (Preview) query.getSingleResult();
            return preview;
        }catch(Exception e){
            return null;
        }
    }
    
}
