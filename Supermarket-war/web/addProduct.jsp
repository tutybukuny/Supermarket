<%-- 
    Document   : updateBook
    Created on : Mar 22, 2017, 4:17:50 PM
    Author     : TienDuc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="entities.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
            <div class="container">
                <div class="panel panel-danger">
                    <div class="panel-heading">Update a product</div>
                    <div class="panel-body">
                        <form action="Management" method="POST" class="form-horizontal" role="form">
                        <input type="hidden" value="confirmAddProduct" name="action"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Name</label>
                                <div class="col-md-9">
                                    <input name="name" type="text" value="${product.name}" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Description</label>
                                <div class="col-md-9">
                                    <input name="description" type="text" value="${product.description}" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Cost</label>
                                <div class="col-md-9">
                                    <input name="cost" type="text" value="${product.cost.longValue()}" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Preview</label>
                                <div class="col-md-9">
                                    <input name="previewImage" type="text" value="${product.previewID.image}" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Product Type</label>
                                <div class="col-md-9">
                                    <select class="form-control" name="productType">
                                        <c:forEach items="${producttypes}" var="pt">
                                            <option value="${pt.id}" 
                                                    <c:choose>
                                                        <c:when test="${product.productTypeID.id==pt.id}">
                                                            selected="true"
                                                        </c:when>
                                                    </c:choose>
                                                    >${pt.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Manufacturers</label>
                                <div class="col-md-9">
                                    <select class="form-control" name="manufacturer">
                                        <c:forEach items="${manufacturers}" var="ma">
                                            <option value="${ma.id}" 
                                                    <c:choose>
                                                        <c:when test="${product.manufacturerID.id==ma.id}">
                                                            selected="true"
                                                        </c:when>
                                                    </c:choose>
                                                    >${ma.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>   
                        </div>
                        <div class="panel-footer">
                            <div class="form-actions right1" style="padding: ">
                                <a href="Management?action=toAllProduct"><input type="button" value="Cancel" class="btn btn-default"></a>
                                <button type="submit" class="btn btn-success">Update</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
