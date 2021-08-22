<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1 class="text-center text-danger my-3">Quản lý sản phẩm</h1>
<c:url value="/admin/products" var="action" />

<c:if test="${errMsg} != null">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>

<form:form method="post" action="${action}" modelAttribute="product" enctype="multipart/form-data">
    <form:errors path="*" cssClass="alert alert-warning" element="div"/>
    <div class="form-group my-3">
        <label for="name">Tên sản phẩm</label>
        <form:input type="text" path="name" id="name" cssClass="form-control"/>
        <form:errors path="name" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group my-3">
        <label for="description">Mô tả</label>
        <form:textarea path="description" id="description" cssClass="form-control"/>
    </div>
    <div class="form-group my-3">
        <label for="price">Giá</label>
        <form:input type="text" path="price" id="price" cssClass="form-control"/>
        <form:errors path="price" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group my-3">
        <label for="cate">Danh mục</label>
        <form:select type="text" path="category" id="cate" cssClass="form-control">
            <c:forEach items="${categories}" var="cat">
                <option value="${cat.id}">${cat.name}</option>
            </c:forEach>
        </form:select>
        <%--<form:input type="text" path="category" id="cate" cssClass="form-control"/>--%>
        <form:errors path="price" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group my-3">
        <label for="file">Ảnh sản phẩm</label>
        <form:input type="file" path="file" id="file" cssClass="form-control"/>
    </div>
    <div class="form-group my-3">
        <input type="submit" value="Add product" class="btn btn-danger"/>
    </div>
</form:form>