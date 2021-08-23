<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/login" var="action" />
<h1 class="text-center text-primary my-3">Đăng nhập</h1>

<c:if test="${param.error != null}">
    <div class="alert alert-warning">
        Đã có lỗi xảy ra, vui lòng thử lại.
    </div>
</c:if>

<form method="post" action="${action}">
    <div class="form-group my-3">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" class="form-control" />
    </div>
    <div class="form-group my-3">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" />
    </div>
    <div class="form-group my-3">
        <input class="btn btn-primary" type="submit" value="Đăng nhập" />
    </div>
</form>
