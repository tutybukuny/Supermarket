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
        <title>All Products</title>
    </head>
        <jsp:include page="content/header.jsp"></jsp:include>
        <div class="container">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-10" style="padding-top: 5px">
                            <p>All Products</p>
                        </div>
                        <div class="col-md-1">
                            <a href="Management?action=toAddProduct" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Add</a>
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
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <%

                                    List<Product> products = (List<Product>) session.getAttribute("products");
                                    for (int i = 0; i < products.size(); i++) {
                                        Product product = products.get(i);
                                %>
                                <tr>

                                    <td><%= product.getId().toString() %></td> 
                                    <td><%= product.getName()%></td>
                                    <td><%= product.getDescription()%></td>
                                    <td><%= product.getCost().longValue() %></td>
                                    <td><%= product.getProductTypeID().getName() %></td>
                                    
                                    <td>
                                        <a href="Management?index=<%=i%>&action=updateProduct" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></a>
                                    </td>
                                    <td>
                                        <a href="Management?index=<%=i%>&action=deleteProduct" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">

                    </div>  
                </div>
            </div>
        </div>
    </body>
</html>
