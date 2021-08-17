<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--<h1>Demo tạo thử truy vấn xuống csdl thông qua Hibernate</h1>
<ul>
<c:forEach var="cat" items="${categories}">
    <li>${cat.id} - ${cat.name}</li>
</c:forEach>
</ul>-->

<h1 class="text-success text-center my-3">Danh mục sản phẩm</h1>
<form class="row" action="">
    <div class="col-9">
        <input class="form-control" type="text" name="kw" placeholder="Nhập từ khóa để tìm" />
    </div>
    <div class="col-3">
        <input type="submit" value="Search" class="btn btn-primary form-control"/>
    </div>
</form>
<hr/>
<div class="row">
    <c:forEach var="p" items="${products}">
        <div class="card col-4 p-2 border-0">
            <img src="<c:url value="images/default.jpg"/>" class="card-img-top" alt="${p.name}">
            <div class="card-body bg-success">
                <h5 class="card-title">${p.name}</h5>
                <p class="card-text">${p.price}</p>
            </div>
        </div>
    </c:forEach>
</div>