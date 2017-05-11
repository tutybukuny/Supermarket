<%-- 
    Document   : index
    Created on : Mar 21, 2017, 10:44:58 PM
    Author     : TienDuc
--%>

<%@page import="API.APIWrapper"%>
<%@page import="entities.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%
    Human human = (Human) session.getAttribute("human");
    if (human == null) {
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
            <div class="login-page">
                <div class="form">
                    <form action="Management" method="POST" class="login-form">
                        <input type="text" name="username" placeholder="username"/>
                        <input type="password" name="password" placeholder="password"/>
                        <input type="hidden" name="action" value="login"/>
                        <button type="submit">login</button>
                        <p class="message">Not registered? <a href="#">Create an account</a></p>
                        <p class="message"><a href="<%= APIWrapper.getDialogLink() %>"><i class="fa fa-facebook-official" style="font-size:15px;color:#5b5998"> Login with facebook</i></a></p>
                    </form>
                </div>
            </div>
        </body>
    </html>
<%
    } else if(human.getDiscriminator().equals("Admin")) {
        response.sendRedirect("allProducts.jsp");
    }else if(human.getDiscriminator().equals("Customer")){
        response.sendRedirect("allCustomerProduct.jsp");
    }
%>