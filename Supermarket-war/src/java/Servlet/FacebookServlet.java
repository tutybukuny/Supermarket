package Servlet;

import API.APIWrapper;
import entities.Account;
import entities.Cart;
import entities.Human;
import entities.Product;
import entities.Selectedproduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbean.*;

/**
 *
 * @author TrungNguyen
 */
public class FacebookServlet extends HttpServlet {

    @EJB
    ProductFacadeLocal productDAO;
    @EJB
    AccountFacadeLocal accountDAO;
    @EJB
    HumanFacadeLocal humanDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String code = request.getParameter("code");

            //Tu code da lay, chuyen thanh access token
            APIWrapper wrapper = new APIWrapper();
            String accessToken = wrapper.getAccessToken(code);
            wrapper.setAccessToken(accessToken);

            //Truy cap thong tin account
            Account account = wrapper.getAccountInfo();
            System.out.println("FacebookID:" + account.getFacebookID());
            boolean accountExist = accountDAO.checkHaveAccFb(account.getFacebookID());
            System.out.println(accountExist);

            if (!accountExist) {
                Human human = new Human();
                human.setDiscriminator("Customer");
                humanDAO.create(human);
                human.getAccountList().add(account);
                System.out.println(human.getId().toString());
                account.setHumanID(human);
                account.setDiscriminator("Customer");
                accountDAO.create(account);
            }
            Human human = humanDAO.getHumanByFbId(account.getFacebookID());
            HttpSession session = request.getSession();
            session.setAttribute("human", human);
            Cart cart = new Cart();
            cart.setHumanID(human);
            List<Selectedproduct> selectedproducts = new ArrayList<Selectedproduct>();
            cart.setSelectedproductList(selectedproducts);
            session.setAttribute("cart", cart);

            allProducts(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void allProducts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> products = productDAO.findAll();
        request.getSession().setAttribute("products", products);
        HttpSession session = request.getSession();
        Human human = (Human) session.getAttribute("human");
        
        if (human.getDiscriminator().equals("Customer")) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allCustomerProduct.jsp");
            dispatcher.forward(request, response);
        }
        
        if (human.getDiscriminator().equals("Admin")) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allProducts.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allCustomerProduct.jsp");
            dispatcher.forward(request, response);
        }
    }
}
