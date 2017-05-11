/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Saleproduct;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TrungNguyen
 */
@Local
public interface SaleproductFacadeLocal {

    void create(Saleproduct saleproduct);

    void edit(Saleproduct saleproduct);

    void remove(Saleproduct saleproduct);

    Saleproduct find(Object id);

    List<Saleproduct> findAll();

    List<Saleproduct> findRange(int[] range);

    int count();
    
}
