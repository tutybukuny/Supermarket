/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Rate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TrungNguyen
 */
@Local
public interface RateFacadeLocal {

    void create(Rate rate);

    void edit(Rate rate);

    void remove(Rate rate);

    Rate find(Object id);

    List<Rate> findAll();

    List<Rate> findRange(int[] range);

    int count();
    
}
