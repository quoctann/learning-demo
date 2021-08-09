<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="nav shadow">
    <li class="nav-item">
        <a class="nav-link active" aria-current="page" href="/">Trang chủ</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#">Liên hệ</a>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Categories</a>
        <ul class="dropdown-menu">
            <c:forEach var="cate" items="${categories}">
                <li><a class="dropdown-item" href="#">${cate.name}</a></li>
            </c:forEach>
        </ul>
    </li>
</ul>

