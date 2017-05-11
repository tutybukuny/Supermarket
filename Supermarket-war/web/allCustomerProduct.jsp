
<%@page import="java.util.List"%>
<%@page import="entities.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import = "java.util.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Products</title>
    </head>
    <jsp:include page="content/header.jsp"></jsp:include>
        <a  href="Management?action=showCart" style="position: fixed;right: 10px;bottom: 60px">
            <img style="z-index: 1; height: 80px; width: 80px"  src="images/cart.png">
        </a>
        <div id="tour" class="bg-1">
            <div class="container">
                <div class="row text-center">
                <%
                    List<Product> products = (List<Product>) session.getAttribute("products");
                    for (int i = 0; i < products.size(); i++) {
                        Product product = products.get(i);
                %>
                <div class="col-sm-3">

                    <div class="thumbnail">
                        <a href="Management?action=detailProduct&id=<%=product.getId()%> "><img  src=<%= product.getPreviewID().getImage()%> alt="Paris"></a>
                        <p><strong><%= product.getName()%></strong></p>
                        <p><%= product.getCost().longValue()%></p>
                        <a href="Management?action=addToCart&id=<%=product.getId()%> " id="<%= product.getName()%>" class=" bt add btn">Add To Cart</a>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
    <div id="googleMap"></div>
    <script>
        function myMap() {
            var myCenter = new google.maps.LatLng(20.910852,  105.574581);
            var mapProp = {center: myCenter, zoom: 18, scrollwheel: false, draggable: false, mapTypeId: google.maps.MapTypeId.ROADMAP};
            var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
            var marker = new google.maps.Marker({position: myCenter});
            marker.setMap(map);
        }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDYOXCS4hrmsGKsY0bcqpNjwhOeTyLNnkw&callback=myMap"></script>
</body>
<script>
        $(".add").click(function () {
            var name = $(this).attr("id");
            alert("Đã thêm " + name + " vào giỏ hàng");
        });
</script>
</html>

