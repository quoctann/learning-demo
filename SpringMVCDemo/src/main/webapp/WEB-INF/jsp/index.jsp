<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- prefix đặt gì cũng được -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
    </head>
    <body>
        <div class="box">
            <h1>Welcome to website ${name}</h1>
        </div>
        <c:url value="/hello-post" var="action"/>
        <form:form class="box" method="POST" action="${action}" modelAttribute="userDemo">
            <spring:message code="label.firstName"/>
            <form:input path="firstName"/>
            <br/>
            <spring:message code="label.lastName"/>
            <form:input path="lastName"/>
            <input type="submit" value="Send"/>
        </form:form>
        <c:if test="${fullName != null}">
            <div class="box">
                <h2>${fullName}</h2>
            </div>
        </c:if>
        <ul>
            <c:forEach var="i" begin="1" end="5">
                <c:choose>
                    <c:when test="${i%2==0}"><li style="color: red;">${i}</li></c:when>
                    <c:when test="${i%2!=0}"><li style="color: orange;">${i}</li></c:when>
                </c:choose>
                
            </c:forEach>
        </ul>
        <ol>
            <c:forEach var="u" items="${users}">
                <li>${u.firstName} ${u.lastName}</li>
            </c:forEach>
        </ol>
        <ul>
            <!-- Cắt chuỗi bằng dấu "," -->
            <c:forTokens var="f" delims="," items="Apple, Lemon, Orange">
                <li>${f}</li>
            </c:forTokens>
        </ul>
    </body>
    <style>
        .box {
            padding: 2rem;
            margin-top: 1rem;
            width: 80vh;
            border: 2px solid #333;
        }
    </style>
</html>
