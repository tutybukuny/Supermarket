/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Birthday;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TrungNguyen
 */
@Local
public interface BirthdayFacadeLocal {

    void create(Birthday birthday);

    void edit(Birthday birthday);

    void remove(Birthday birthday);

    Birthday find(Object id);

    List<Birthday> findAll();

    List<Birthday> findRange(int[] range);

    int count();
    
}
