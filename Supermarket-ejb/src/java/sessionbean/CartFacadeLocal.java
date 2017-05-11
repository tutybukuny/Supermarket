/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Cart;
import entities.Product;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TrungNguyen
 */
@Local
public interface CartFacadeLocal {

    void create(Cart cart);

    void edit(Cart cart);

    void remove(Cart cart);

    Cart find(Object id);

    List<Cart> findAll();

    List<Cart> findRange(int[] range);

    int count();
     
}
