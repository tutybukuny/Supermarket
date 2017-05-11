/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Preview;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TrungNguyen
 */
@Local
public interface PreviewFacadeLocal {

    void create(Preview preview);

    void edit(Preview preview);

    void remove(Preview preview);

    Preview find(Object id);

    List<Preview> findAll();

    List<Preview> findRange(int[] range);

    int count();
    
    Preview findByImage(String image);
    
}
