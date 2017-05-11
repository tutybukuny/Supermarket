/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import entities.*;
import sessionbean.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TrungNguyen
 */
public class Management extends HttpServlet {
    
    @EJB
    HumanFacadeLocal humanDAO;
    @EJB
    AccountFacadeLocal accountDAO;
    @EJB
    ProductFacadeLocal productDAO;
    @EJB
    ManufacturerFacadeLocal manufacturerDAO;
    @EJB
    ProducttypeFacadeLocal productTypeDAO;
    @EJB
    CartFacadeLocal cartDAO;
    @EJB
    PreviewFacadeLocal previewDAO;
    @EJB
    ProductsetFacadeLocal productsetDAO;
    @EJB
    SelectedproductFacadeLocal selectedproductDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/deleteBook.jsp");
        
        System.out.println(dis.toString());
        
        if (request.getSession().getAttribute("human") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        String action = request.getParameter("action");
        if (action.equals("toAllProduct")) {
            allProducts(request, response);
        } else if (action.equals("toAddProduct")) {
            addProducts(request, response);
        } else if (action.equals("updateProduct")) {
            updateProduct(request, response);
        } else if (action.equals("deleteProduct")) {
            deleteProduct(request, response);
        } else if (action.equals("logout")) {
            logout(request, response);
        } else if (action.equals("addToCart")) {
            addCart(request, response);
        } else if (action.equals("toOrder")) {
            order(request, response);
        } else if (action.equals("toShowAllCart")) {
            showAllCarts(request, response);
        } else if (action.equals("detailCart")) {
            detailCart(request, response);
        } else if (action.equals("detailProduct")) {
            detailProduct(request, response);
        } else if (action.equals("deleteProductInCart")) {
            deleteProductInCart(request, response);
        } else if (action.equals("toAllPub")) {
            
        } else if (action.equals("toAddPub")) {
            
        } else if (action.equals("updatePub")) {
            
        } else if (action.equals("deletePub")) {
            
        } else if (action.equals("toAllType")) {
            
        } else if (action.equals("toAddType")) {
            
        } else if (action.equals("updateType")) {
            
        } else if (action.equals("deleteType")) {
            
        } else if (action.equals("getProductByType")) {
            getProductByType(request, response);
        } else if (action.equals("showCart")) {
            showCart(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        
        if (action.equals("login")) {
            checkLogin(request, response);
        } else if (action.equals("signup")) {
//            signup(request, response);
        }
        
        if (request.getSession().getAttribute("human") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        if (action.equals("confirmUpdateProduct")) {
            confirmUpdateProduct(request, response);
        } else if (action.equals("confirmAddProduct")) {
            confirmAddProduct(request, response);
        } else if (action.equals("confirmDeleteProduct")) {
            confirmDeleteProduct(request, response);
        } else if (action.equals("confirmUpdateAuthor")) {
            
        } else if (action.equals("addAuthor")) {
            
        } else if (action.equals("confirmDeleteAuthor")) {
            
        } else if (action.equals("confirmUpdatePub")) {
            
        } else if (action.equals("addPub")) {
            
        } else if (action.equals("confirmDeletePub")) {
            
        } else if (action.equals("confirmUpdateType")) {
            
        } else if (action.equals("addType")) {
            
        } else if (action.equals("confirmDeleteType")) {
            
        }
    }
    
    private void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.find(id);
        System.out.println(id + " id");
        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Selectedproduct selectedproduct = new Selectedproduct();
        selectedproduct.setProductID(product);
        selectedproduct.setCartID(cart);
        cart.getSelectedproductList().add(selectedproduct);
        session.setAttribute("cart", cart);
        allProducts(request, response);
    }
    
    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cartDAO.create(cart);
        allProducts(request, response);
    }
    
    private void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        Account acc = new Account();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        acc.setUsername(username);
        acc.setPassword(password);
        
        RequestDispatcher dis;
        
        if (accountDAO.checkLogin(acc)) {
            HttpSession session = request.getSession();
            Human human = humanDAO.getHumanByAccount(acc);
            session.setAttribute("human", human);
            Cart cart = new Cart();
            cart.setHumanID(human);
            List<Selectedproduct> selectedproducts = new ArrayList<>();
            cart.setSelectedproductList(selectedproducts);
            session.setAttribute("cart", cart);
            try {
                allProducts(request, response);
            } catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
            
        } else {
            dis = getServletContext().getRequestDispatcher("/index.jsp");
            try {
                dis.forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("human");
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }
    
    private void allProducts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> products = productDAO.findAll();
        request.getSession().setAttribute("products", products);
        HttpSession session = request.getSession();
        Human human = (Human) session.getAttribute("human");
        if (human.getDiscriminator().equals("Admin")) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allProducts.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allCustomerProduct.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void getProductByType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int productType = Integer.parseInt(request.getParameter("productType"));
        List<Product> products = productDAO.findByType(productType);
        request.getSession().setAttribute("products", products);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allCustomerProduct.jsp");
        dispatcher.forward(request, response);
    }
    
    private void addProducts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Manufacturer> manufacturers = manufacturerDAO.findAll();
        List<Producttype> producttypes = productTypeDAO.findAll();
        request.setAttribute("manufacturers", manufacturers);
        request.setAttribute("producttypes", producttypes);
        
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/addProduct.jsp");
        dis.forward(request, response);
    }
    
    private void showAllCarts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cart> carts = cartDAO.findAll();
        System.out.println("cart size " + carts.size());
        request.setAttribute("carts", carts);
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/showAllCart.jsp");
        dis.forward(request, response);
    }
    
    private void confirmAddProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Product product = new Product();
        
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(Integer.parseInt((String) request.getParameter("manufacturer")));
        Producttype producttype = new Producttype();
        producttype.setId(Integer.parseInt((String) request.getParameter("productType")));
        
        Preview preview = new Preview();
        String image = request.getParameter("previewImage");
        preview.setImage(image);
        previewDAO.create(preview);
        preview = previewDAO.findByImage(image);
        
        product.setManufacturerID(manufacturer);
        product.setProductTypeID(producttype);
        product.setPreviewID(preview);
        product.setProductSetID(productsetDAO.find(1));
        product.setName((String) request.getParameter("name"));
        product.setDescription((String) request.getParameter("description"));
        product.setCost(Float.parseFloat((String) request.getParameter("cost")));
        
        productDAO.create(product);
        allProducts(request, response);
    }
    
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = (List<Product>) request.getSession().getAttribute("products");
        List<Manufacturer> manufacturers = manufacturerDAO.findAll();
        List<Producttype> producttypes = productTypeDAO.findAll();
        
        request.setAttribute("product", products.get(Integer.parseInt(request.getParameter("index"))));
        request.setAttribute("manufacturers", manufacturers);
        request.setAttribute("producttypes", producttypes);
        
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/updateProduct.jsp");
        dis.forward(request, response);
    }
    
    private void showCart(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/showCart.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }
    
    private void confirmUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = productDAO.find(Integer.parseInt((String) request.getParameter("productID")));
        
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(Integer.parseInt((String) request.getParameter("manufacturer")));
        Producttype producttype = new Producttype();
        producttype.setId(Integer.parseInt((String) request.getParameter("productType")));
        
        String previewImage = request.getParameter("previewImage");
        Preview preview = product.getPreviewID();
        preview.setImage(previewImage);
        previewDAO.edit(preview);
        
        product.setManufacturerID(manufacturer);
        product.setProductTypeID(producttype);
        product.setPreviewID(preview);
        product.setName((String) request.getParameter("name"));
        product.setDescription((String) request.getParameter("description"));
        product.setCost(Float.parseFloat((String) request.getParameter("cost")));
        
        productDAO.edit(product);
        allProducts(request, response);
    }
    
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<Product> products = (List<Product>) request.getSession().getAttribute("products");
        
        request.setAttribute("product", products.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/deleteProduct.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void confirmDeleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int productID = Integer.parseInt((String) request.getParameter("productID"));
        Product product = productDAO.find(productID);
        productDAO.remove(product);
        allProducts(request, response);
        
    }

    //ProductType
    private void allProductTypes(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Producttype> productTypes = productTypeDAO.findAll();
        request.getSession().setAttribute("productTypes", productTypes);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allTypes.jsp");
        dispatcher.forward(request, response);
    }
    
    private void updateProductType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producttype> productTypes = (List<Producttype>) request.getSession().getAttribute("productTypes");
        request.setAttribute("productType", productTypes.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/updateType.jsp");
        dis.forward(request, response);
    }
    
    private void confirmUpdateProductType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Producttype productType = productTypeDAO.find(Integer.parseInt((String) request.getParameter("productTypeID")));
        productType.setName((String) request.getParameter("name"));
        productType.setDescription((String) request.getParameter("description"));
        productTypeDAO.edit(productType);
        allProductTypes(request, response);
    }
    
    private void addProductType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/addType.jsp");
        dis.forward(request, response);
    }
    
    private void confirmAddProductType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Producttype productType = new Producttype();
        productType.setName((String) request.getParameter("name"));
        productType.setDescription((String) request.getParameter("description"));
        productTypeDAO.create(productType);
        allProductTypes(request, response);
    }
    
    private void deleteProductType(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<Producttype> productTypes = (List<Producttype>) request.getSession().getAttribute("productTypes");
        request.setAttribute("productType", productTypes.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/deleteType.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void confirmDeleteProductType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int productTypeID = Integer.parseInt((String) request.getParameter("productTypeID"));
        Producttype productType = productTypeDAO.find(productTypeID);
        productTypeDAO.remove(productType);
        allProductTypes(request, response);
    }

    //Manufacturer
    private void allManufacturers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Manufacturer> manufacturers = manufacturerDAO.findAll();
        request.getSession().setAttribute("manufacturers", manufacturers);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allManufacturers.jsp");
        dispatcher.forward(request, response);
    }
    
    private void updateManufacturer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Manufacturer> listManufacturers = (List<Manufacturer>) request.getSession().getAttribute("manufacturers");
        request.setAttribute("manufacturer", listManufacturers.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/updateManufacturer.jsp");
        dis.forward(request, response);
    }
    
    private void confirmUpdateManufacturer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Manufacturer manufacturer = manufacturerDAO.find(Integer.parseInt((String) request.getParameter("manufacturerID")));
        manufacturer.setName((String) request.getParameter("name"));
        manufacturer.setDescription((String) request.getParameter("description"));
        manufacturerDAO.edit(manufacturer);
        allManufacturers(request, response);
    }
    
    private void addManufacturer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/addManufacturer.jsp");
        dis.forward(request, response);
    }
    
    private void confirmAddManufacturer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName((String) request.getParameter("name"));
        manufacturer.setDescription((String) request.getParameter("description"));
        manufacturerDAO.create(manufacturer);
        allManufacturers(request, response);
    }
    
    private void deleteManufacturer(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<Manufacturer> listManufacturer = (List<Manufacturer>) request.getSession().getAttribute("manufacturers");
        request.setAttribute("manufacturer", listManufacturer.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/deleteManufacturer.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void confirmDeleteAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int manufacturerID = Integer.parseInt((String) request.getParameter("manufacturerID"));
        Manufacturer manufacturer = manufacturerDAO.find(manufacturerID);
        manufacturerDAO.remove(manufacturer);
        allManufacturers(request, response);
    }

    //CART
    private void detailCart(HttpServletRequest request, HttpServletResponse response) {
        int index = Integer.parseInt(request.getParameter("index"));
        Cart cart = cartDAO.find(index);
        request.setAttribute("cart", cart);
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/detailCart.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }
    
    private void detailProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.find(id);
        request.setAttribute("product", product);
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/detailProduct.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }
    
    private void deleteProductInCart(HttpServletRequest request, HttpServletResponse response) {
        int index = Integer.parseInt(request.getParameter("index"));
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.getSelectedproductList().remove(index);
        session.setAttribute("cart", cart);
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/showCart.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }
}
