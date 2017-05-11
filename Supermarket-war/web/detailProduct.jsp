<%-- 
    Document   : detailProduct
    Created on : May 2, 2017, 8:18:55 PM
    Author     : huutien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${product.name}</title>
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
            <div id="fb-root"></div>
            <script>(function (d, s, id) {
                    var js, fjs = d.getElementsByTagName(s)[0];
                    if (d.getElementById(id))
                        return;
                    js = d.createElement(s);
                    js.id = id;
                    js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.9&appId=1055526377880407";
                    fjs.parentNode.insertBefore(js, fjs);
                }(document, 'script', 'facebook-jssdk'));</script>
            <div class="container-fluid bg-2 text-center">
                <h3 class="margin">${product.name}</h3>
            <img src="${product.previewID.image}" class="img-responsive margin" style="display:inline" alt="Bird" width="350" height="350">
            <!--<h3>I'm ${product.name}</h3>-->
        </div>

        <!-- Second Container -->
        <div class="container-fluid bg-3 text-center">
            <h3 class="margin">What I have?</h3>
            <p>${product.description}</p>
            <a href="Management?action=addToCart&id=${product.id}" class="btn btn-danger">
                <span class="glyphicon glyphicon-plus"></span> Add To Cart
            </a>
            <div class="fb-share-button" data-href="http://localhost:8080/Supermarket-war/Management?action=detailProduct&id=5" data-layout="button_count" data-size="large" data-mobile-iframe="true"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse">Chia sẻ</a>
            </div>
        </div>
    </body>
</html>


