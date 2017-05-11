<%-- 
    Document   : occho
    Created on : Mar 22, 2017, 12:03:14 PM
    Author     : occho
--%>

<%@page import="entities.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import = "java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Cart</title>
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
                                    <td><b> Total Cost</b></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cart" items="${carts}">
                                <tr>

                                    <td>${cart.id}</td> 
                                    <td>${cart.totalCost.longValue()}</td>
                                    
                                    <td>
                                        <a href="Management?index=${cart.id}&action=detailCart" class="btn btn-success">Detail</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <div class="form-actions right1" style="padding-left: 40%;padding-right: 40% ">
                            <a href="Management?action=toAllProduct" class="btn btn-default">Back</a>
                            </div>
                    </div>  
                </div>
            </div>
        </div>
    </body>
</html>