<%-- 
    Document   : deleteBook
    Created on : Mar 23, 2017, 12:01:47 AM
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
        <title>Delete Product</title>
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
            <div class="container">
                <div class="panel panel-danger">
                    <div class="panel-heading">Delete a Product</div>
                    <div class="panel-body">
                        <form action="Management" method="POST" class="form-horizontal" role="form">
                            <input type="hidden" value="${product.id}" name="productID" />
                        <input type="hidden" value="confirmDeleteProduct" name="action"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Name</label>
                                <div class="col-md-9">
                                    <input name="name" type="text" value="${product.name}" readonly class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Description</label>
                                <div class="col-md-9">
                                    <input name="description" type="text" value="${product.description}" readonly class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Cost</label>
                                <div class="col-md-9">
                                    <input name="cost" type="text" value="${product.cost}" readonly class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Product Type</label>
                                <div class="col-md-9">
                                    <input name="cost" type="text" value="${product.productTypeID.name}" readonly class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Manufacturer</label>
                                <div class="col-md-9">
                                    <input name="cost" type="text" value="${product.manufacturerID.name}" readonly class="form-control input-sm"/>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="form-actions right1" style="padding: ">
                                <a href="Management?action=toAllProduct"><input type="button" value="Cancel" class="btn btn-default"></a>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
