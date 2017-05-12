/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import entities.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.JSONArray;
import org.json.JSONObject;
import sessionbean.*;

/**
 *
 * @author Ngọc Thiện
 */
@Stateless
@LocalBean
@Path("/service")
public class RestfulWebservice {

    @EJB
    HumanFacadeLocal humanDAO;
    @EJB
    AccountFacadeLocal accountDAO;
    @EJB
    ProductFacadeLocal productDAO;
    @EJB
    CartFacadeLocal cartDAO;
    @EJB
    SelectedproductFacadeLocal selectedproductDAO;

    public RestfulWebservice() {
    }

    @POST
    @Produces("application/json")
    public String service(String json) {
        JSONObject req = new JSONObject(json);
        String action = req.getString("action");

        if (action.equals("login")) {
            return login(req);
        } else if (action.equals("getAllProducts")) {
            return getAllProducts(req);
        } else if (action.equals("sendCart")) {
            return addCart(req);
        }

        return "{error:\"Something went wrong\"}";
    }

    private String login(JSONObject req) {
        Account acc = new Account();
        JSONObject json = req.getJSONObject("json");
        acc.setUsername(json.getString("username"));
        acc.setPassword(json.getString("password"));

        Human human = humanDAO.getHumanByAccount(acc);
        JSONObject jobj = new JSONObject();

        if (human != null) {
            jobj.put("result", "succes");
            jobj.put("humanId", human.getId().intValue());
        } else {
            jobj.put("result", "error");
        }

        return jobj.toString();
    }

    private String getAllProducts(JSONObject req) {
        JSONArray jsArrayProducts = new JSONArray();
        List<Product> list = productDAO.findAll();

        for (Product p : list) {
            JSONObject jsObjProduct = new JSONObject();
            jsObjProduct.put("id", p.getId().intValue());
            jsObjProduct.put("name", p.getName());
            jsObjProduct.put("description", p.getDescription());
            jsObjProduct.put("cost", p.getCost().floatValue());

            JSONObject jsPreview = new JSONObject();
            jsPreview.put("id", p.getPreviewID().getId().intValue());
            jsPreview.put("intro", p.getPreviewID().getIntro());
            jsPreview.put("image", p.getPreviewID().getImage());
            jsObjProduct.put("preview", jsPreview);

            JSONObject jsManufacturer = new JSONObject();
            jsManufacturer.put("id", p.getManufacturerID().getId().intValue());
            jsManufacturer.put("name", p.getManufacturerID().getName());
            jsManufacturer.put("des", p.getManufacturerID().getName());
            jsObjProduct.put("manufacturer", jsManufacturer);

            jsArrayProducts.put(jsObjProduct);
        }

        JSONObject parameters = new JSONObject();
        parameters.put("products", jsArrayProducts);
        return parameters.toString();
    }

    private String addCart(JSONObject req) {

        Cart cart = new Cart();
        cart.setSelectedproductList(new ArrayList<Selectedproduct>());

        JSONObject cartObj = req.getJSONObject("cart");
        float totalCost = (float) cartObj.getDouble("totalCost");
        JSONArray spArray = cartObj.getJSONArray("selectproducts");

        for (int i = 0; i < spArray.length(); i++) {
            Selectedproduct sp = new Selectedproduct();
            JSONObject jsSp = spArray.getJSONObject(i);
            int productId = jsSp.getInt("productId");
            Product product = productDAO.find(productId);
            sp.setProductID(product);
            sp.setCartID(cart);
            cart.getSelectedproductList().add(sp);
        }

        JSONObject humanObj = cartObj.getJSONObject("human");
        int humanId = humanObj.getInt("humanId");
        Human human = humanDAO.find(humanId);
        cart.setHumanID(human);
        cart.setTotalCost(totalCost);
        cartDAO.create(cart);

        JSONObject parameters = new JSONObject();
        parameters.put("result", "ok");
        return parameters.toString();
    }
}
