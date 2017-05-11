/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Selectedproduct;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TrungNguyen
 */
@Local
public interface SelectedproductFacadeLocal {

    void create(Selectedproduct selectedproduct);

    void edit(Selectedproduct selectedproduct);

    void remove(Selectedproduct selectedproduct);

    Selectedproduct find(Object id);

    List<Selectedproduct> findAll();

    List<Selectedproduct> findRange(int[] range);

    int count();
    
}
