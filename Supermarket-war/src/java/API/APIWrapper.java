/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Utils.NetworkUtils;
import com.google.gson.Gson;
import entities.Account;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author TrungNguyen
 */
public class APIWrapper {

    private static String appID = "419110488447485";
    private static String appSecret = "26eec6e44d97987bbbf6afba688c28b6";
    private static String redirectUrl = "http://localhost:8080/Supermarket-war/FacebookServlet";
    private String accessToken;
    private Gson gson;

    public APIWrapper() {
    }

    public APIWrapper(Gson gson) {
        this.gson = gson;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public static String getDialogLink() {
        String dialogLink = "https://www.facebook.com/dialog/oauth?client_id=%s&redirect_uri=%s";
        return String.format(dialogLink, appID, redirectUrl);
    }

    public String getAccessToken(String code) {
        String accessTokenLink = "https://graph.facebook.com/oauth/access_token?"
                + "client_id=%s"
                + "&client_secret=%s"
                + "&redirect_uri=%s"
                + "&code=%s";

        accessTokenLink = String.format(accessTokenLink, appID, appSecret, redirectUrl, code);

        String result = NetworkUtils.GetResult(accessTokenLink);
        String token = result.substring(result.indexOf(":") + 2, result.indexOf(",") - 1);
        return token;
    }

    public Account getAccountInfo() {
        String infoUrl = "https://graph.facebook.com/me?access_token=%s&fields=id,name,link";
        infoUrl = String.format(infoUrl, this.accessToken);

        String result = NetworkUtils.GetResult(infoUrl);
        System.out.println("Result :" + result);

        JSONObject jobj;
        Account account = new Account();
        try {
            jobj = new JSONObject(result);
           
            account.setFacebookLink(jobj.getString("link"));
            account.setFacebookID(jobj.getString("id"));      
        } catch (JSONException ex) {
            Logger.getLogger(APIWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return account;
    }

}
