<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/register" var="action" />

<h1 class="text-center text-primary my-3">Đăng ký</h1>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        <span>${errMsg}</span>
    </div>
</c:if>

<form:form method="post" action="${action}" modelAttribute="user">
    <div class="form-group my-3">
        <label for="username">Username</label>
        <form:input type="text" id="username" path="username" class="form-control" />
    </div>
    <div class="form-group my-3">
        <label for="password">Password</label>
        <form:input type="password" id="password" path="password" class="form-control" />
    </div>
    <div class="form-group my-3">
        <label for="confirm-password">Confirm password</label>
        <form:input type="password" id="confirm-password" path="confirmPassword" class="form-control" />
    </div>
    <div class="form-group my-3">
        <label for="firstname">First name</label>
        <form:input type="text" id="firstname" path="firstName" class="form-control" />
    </div>
    <div class="form-group my-3">
        <label for="lastname">Last name</label>
        <form:input type="text" id="lastname" path="lastName" class="form-control" />
    </div>
    <div class="form-group my-3">
        <label for="email">Email</label>
        <form:input type="email" id="email" path="email" class="form-control" />
    </div>
    <div class="form-group my-3">
        <label for="phone">Phone</label>
        <form:input type="text" id="phone" path="phone" class="form-control" />
    </div>
    <div class="form-group my-3">
        <input class="btn btn-info" type="submit" value="Đăng ký" />
    </div>
</form:form>