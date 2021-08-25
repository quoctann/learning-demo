<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="text-center text-primary my-3">Giỏ hàng</h2>

<table class="table table-striped my-3">
    <tr>
        <th>Mã sản phẩm</th>
        <th>Tên sản phẩm</th>
        <th>Đơn giá</th>
        <th>Số lượng</th>
    </tr>
    <c:forEach var="c" items="${carts}">
        <tr>
            <td>${c.productId}</td>
            <td>${c.name}</td>
            <td>${c.price}</td>
            <td>${c.count}</td>
        </tr>
    </c:forEach>
</table>
