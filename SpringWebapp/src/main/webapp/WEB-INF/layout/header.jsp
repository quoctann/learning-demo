<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="nav bg-dark ">
    <li class="nav-item">
        <a class="nav-link active link-light" aria-current="page" href="<c:url value="/"/>">Trang chủ</a>
    </li>
    <li class="nav-item">
        <a class="nav-link link-light" href="<c:url value="/cart"/>">Giỏ hàng</a>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle link-light" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Categories</a>
        <ul class="dropdown-menu">
            <c:forEach var="cate" items="${categories}">
                <li><a class="dropdown-item" href="#">${cate.name}</a></li>
                </c:forEach>
        </ul>
    </li>
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <li class="nav-item">
            <a class="nav-link link-danger" href="<c:url value="/login"/>">Đăng nhập</a>
        </li>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <li class="nav-item">
            <a class="nav-link link-danger" href="<c:url value="/"/>">${pageContext.request.userPrincipal.name}</a>
        </li>
    </c:if>
</ul>

