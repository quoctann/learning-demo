<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--<h1>Demo tạo thử truy vấn xuống csdl thông qua Hibernate</h1>
<ul>
<c:forEach var="cat" items="${categories}">
    <li>${cat.id} - ${cat.name}</li>
</c:forEach>
</ul>-->

<h1 class="text-warning text-center">Danh mục sản phẩm</h1>
<c:forEach var="p" items="${products}">
    <div class="card">
        <img src="<c:url value="images/default.jpg"/>" class="card-img-top" alt="${p.name}">
        <div class="card-body">
            <h5 class="card-title">${p.name}</h5>
            <p class="card-text">${p.price}</p>
            
        </div>
    </div>
</c:forEach>