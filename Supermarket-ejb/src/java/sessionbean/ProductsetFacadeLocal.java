/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Productset;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TrungNguyen
 */
@Local
public interface ProductsetFacadeLocal {

    void create(Productset productset);

    void edit(Productset productset);

    void remove(Productset productset);

    Productset find(Object id);

    List<Productset> findAll();

    List<Productset> findRange(int[] range);

    int count();
    
}
