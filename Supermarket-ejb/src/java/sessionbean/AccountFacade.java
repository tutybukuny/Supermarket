/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Account;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TrungNguyen
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> implements AccountFacadeLocal {
    @PersistenceContext(unitName = "Supermarket-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    @Override
    public boolean checkLogin(Account acc) {
        String jpql = "SELECT a FROM Account a WHERE a.username = :username AND a.password = :password";
        Query query = em.createQuery(jpql);
        query.setParameter("username", acc.getUsername());
        query.setParameter("password", acc.getPassword());
        try{
            query.getSingleResult();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
     @Override
    public boolean checkHaveAccFb(String facebookID) {
        String jpql = "SELECT a FROM Account a WHERE a.facebookID = :facebookID";
        Query query = em.createQuery(jpql);
        query.setParameter("facebookID", facebookID);
        try{
            query.getSingleResult();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public void insertAccount(Account acc) {
        String jpql = "SELECT a FROM Account a WHERE a.username = :username AND a.password = :password";
        Query query = em.createQuery(jpql);
        
    }
   
}
