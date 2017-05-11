/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Account;
import entities.Human;
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
public class HumanFacade extends AbstractFacade<Human> implements HumanFacadeLocal {

    @PersistenceContext(unitName = "Supermarket-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HumanFacade() {
        super(Human.class);
    }

   @Override
    public Human getHumanByAccount(Account acc) {
        Human human = null;
        Query query = getEntityManager().createNamedQuery(Human.class.getSimpleName() + ".getHumanByAccount", Human.class);
        query.setParameter("username", acc.getUsername());
        query.setParameter("password", acc.getPassword());
        List<Human> list = query.getResultList();
        for (Human i : list) {
            for (Account j : i.getAccountList()) {
                if (j.getUsername()!=null && j.getUsername().equals(acc.getUsername()) && j.getPassword().equals(acc.getPassword())) {
                    human = i;
                }
            }
        }
        return human;
    }

    @Override
    public Human getHumanByFbId(String facebookID) {
        Human human = null;
        Query query = getEntityManager().createNamedQuery(Human.class.getSimpleName() + ".getHumanByFbID", Human.class);
        query.setParameter("facebookID", facebookID);
        List<Human> list = query.getResultList();
        for (Human h : list) {
            for (Account j : h.getAccountList()) {
                if (j.getFacebookID() != null && j.getFacebookID().equals(facebookID)) {
                    human = h;
                }
            }
        }
        return human;
    }
}
