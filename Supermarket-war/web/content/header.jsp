<%-- 
    Document   : _menu
    Created on : May 5, 2016, 8:51:30 PM
    Author     : tutyb_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery-2.2.3.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<style>
    #googleMap {
        width: 100%;
        height: 400px;
        z-index: -1;
    }  
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #CC1D1D;
        margin-bottom:50px;
    }

    li {
        float: left;
    }
    .container {
        padding: 80px 120px;
    }
    li a {
        display: inline-block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    li a:hover, .dropdown:hover{
        background-color: #E43E3E;
        color: #ffffff;
        text-decoration: none;
    }
    body {
        font: 400 15px/1.8 Lato, sans-serif;
    }
    .bg-1 {
        background: #ffffff;
        color: #bdbdbd;
    }
    .bg-1 p {font-style: italic;}
    .list-group-item:first-child {
        border-top-right-radius: 0;
        border-top-left-radius: 0;
    }
    .list-group-item:last-child {
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }
    .thumbnail {
        border: none;
        border-radius: 0;
        border-style: solid;
        border-color: #E43E3E;

    }
    .thumbnail p {
        color: #555;
    }
    .bt {
        padding: 10px 20px;
        background-color: #CC1D1D;
        color: #f1f1f1;
        border-radius: 0;
        transition: .2s;
    }
    .bt:hover, .bt:focus {
        border: 1px solid #333;
        background-color: #E43E3E;
        color: #000;
    }
    .thumbnail>a>img {
        display: block;
        width: 100%;
        height: 150px;
    }
    .bg-2 { 
        background-color: #1abc9c; /* Green */
        color: #ffffff;
    }
    .bg-3 { 
        background-color: #474e5d; /* Dark Blue */
        color: #ffffff;
    }
    .container-fluid {
        padding-top: 70px;
        padding-bottom: 70px;
    }
</style>

<ul>
    <li><a style="font-size: medium" href="Management?action=toAllBook">Mediamart</a></li>
    <li><a href="index.jsp">Home</a></li>

    <c:choose>
        <c:when test="${sessionScope.human.discriminator == 'Admin'}">
            <li><a href="Management?action=toAllProduct">Product</a></li>
            <li><a href="Management?action=toShowAllCart">All Cart</a></li>
            </c:when>
            <c:when test="${sessionScope.human.discriminator == 'Customer'}">
            <li><a href="Management?action=getProductByType&productType=2">Tủ lạnh</a></li>
            <li><a href="Management?action=getProductByType&productType=1">Điện thoại</a></li>
            </c:when>
        </c:choose> 
        <c:choose>
            <c:when test="${sessionScope.human!=null}">
            <li style="float:  right"><a href="Management?action=logout"><span class="glyphicon glyphicon-log-out"></span>Log Out</a></li>
            </c:when>
            <c:otherwise>
            <li  style="float:  right"><a href="signup.jsp"><span class="glyphicon glyphicon-user"></span> Đăng ký</a></li>
            <li  style="float:  right"><a href="index.jsp"><span class="glyphicon glyphicon-log-in"></span> Đăng nhập</a></li>
            </c:otherwise>
        </c:choose>
</ul>
