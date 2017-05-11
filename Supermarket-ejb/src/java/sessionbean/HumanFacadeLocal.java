/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Account;
import entities.Human;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TrungNguyen
 */
@Local
public interface HumanFacadeLocal {

    void create(Human human);

    void edit(Human human);

    void remove(Human human);

    Human find(Object id);

    List<Human> findAll();

    List<Human> findRange(int[] range);

    int count();
    
    Human getHumanByAccount(Account acc);
    
    Human getHumanByFbId(String facebookID);
    
}
