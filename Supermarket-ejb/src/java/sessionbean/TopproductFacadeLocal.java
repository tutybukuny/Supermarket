/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Topproduct;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TrungNguyen
 */
@Local
public interface TopproductFacadeLocal {

    void create(Topproduct topproduct);

    void edit(Topproduct topproduct);

    void remove(Topproduct topproduct);

    Topproduct find(Object id);

    List<Topproduct> findAll();

    List<Topproduct> findRange(int[] range);

    int count();
    
}
