<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!--<h1>Demo tạo thử truy vấn xuống csdl thông qua Hibernate</h1>
<ul>
<c:forEach var="cat" items="${categories}">
    <li>${cat.id} - ${cat.name}</li>
</c:forEach>
</ul>-->

<h1 class="text-success text-center my-3">Danh mục sản phẩm</h1>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="my-3">
        <a href="<c:url value="/admin/products"/>" class="btn btn-secondary">Quản lý sản phẩm</a>
    </div>
</sec:authorize>
<form class="row" action="">
    <div class="col-9">
        <input class="form-control" type="text" name="kw" placeholder="Nhập từ khóa để tìm" />
    </div>
    <div class="col-3">
        <input type="submit" value="Search" class="btn btn-primary form-control"/>
    </div>
</form>
<hr/>

<div>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(counter/6)}" var="i">
                <li class="page-item"><a class="page-link" href="<c:url value="/"/>?page=${i}">${i}</a></li>

            </c:forEach>
        </ul>
    </nav>
</div>

<div class="row">
    <c:forEach var="p" items="${products}">
        <div class="card col-4 p-2 border-0">
            <c:if test="${p.image != null && p.image.startsWith('https') == true}">
                <img src="<c:url value="${p.image}"/>" class="card-img-top" alt="${p.name}">
            </c:if>
            <c:if test="${p.image == null || p.image.startsWith('https') == false}">
                <img src="<c:url value="images/default.jpg"/>" class="card-img-top" alt="${p.name}">
            </c:if>
            <div class="card-body bg-success">
                <h5 class="card-title">${p.name}</h5>
                <p class="card-text">${p.price}</p>
            </div>
            <div class="card-footer bg-dark">
                <!--Chặn sự kiện click mặc định bằng javascript:;-->
                <a href="javascript:;" class="btn btn-success" onclick="addToCart(${p.id})">Thêm vào giỏ</a>
                <a class="btn btn-danger">Mua ngay</a>
            </div>
        </div>
    </c:forEach>
</div>