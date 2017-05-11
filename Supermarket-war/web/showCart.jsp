<%-- 
    Document   : allBooks
    Created on : Mar 22, 2017, 12:03:14 PM
    Author     : TienDuc
--%>

<%@page import="entities.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import = "java.util.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
    </head>
        <jsp:include page="content/header.jsp"></jsp:include>
        <div class="container">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-10" style="padding-top: 5px">
                            <p>Cart</p>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <td><b>ID</b></td>
                                    <td><b>Name</b></td>
                                    <td><b>Description</b></td>
                                    <td><b>Cost</b></td>
                                    <td><b>Type</b></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    float total;
                                    total = 0;
                                    Cart cart = (Cart) session.getAttribute("cart");
                                    List<Selectedproduct> products = cart.getSelectedproductList();
                                    for (int i = 0; i < products.size(); i++) {
                                        Product product = products.get(i).getProductID();
                                        total += product.getCost();
                                %>
                                <tr>

                                    <td><%= product.getId().toString() %></td> 
                                    <td><%= product.getName()%></td>
                                    <td><%= product.getDescription()%></td>
                                    <td><%= product.getCost().longValue() %></td>
                                    <td><%= product.getProductTypeID().getName() %></td>
                                    
                                    <td>
                                        <a href="Management?index=<%=i%>&action=deleteProductInCart" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
                                    </td>
                                </tr>
                                
                                <%
                                    }
                                    Float t = new Float(total);
                                    cart.setTotalCost(total);
                                %>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><b>Total Cost: <%= t.longValue() %><b></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <div class="form-actions right1" style="padding-left: 40%;padding-right: 40% ">
                            <a href="Management?action=toAllProduct" class="btn btn-default">Back</a>
                            <a href="Management?action=toOrder" class="btn btn-default">Order</a>
                            </div>
                    </div>  
                </div>
            </div>
        </div>
    </body>
</html>
