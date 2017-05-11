/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author huutien
 */
public class NetworkUtils {
    
    public static String GetResult(String url){
        try{
            return Request.Get(url).setHeader("Accept-Charset","utf-8")
                    .execute().returnContent().asString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
