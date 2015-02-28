<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="instance.instances"/></title>
</head>
<body>
<h1><spring:message code="instance.instances"/></h1>
<table>
    <thead>
    <tr>
        <td><spring:message code="instance.name"/></td>
        <td><spring:message code="instance.module"/></td>
        <td><spring:message code="instance.actions"/></td>
    </tr>
    </thead>
    <c:forEach var="instance" items="${instances}">
        <tr>
            <td>${instance.name}</td>
            <td>${instance.module.name}</td>
            <td>
                <a href="viewInstance?id=${instance.id}"><spring:message code="instance.view"/></a>
                <a href="editInstance?id=${instance.id}"><spring:message code="instance.edit"/></a>
                <a href="executeInstance?id=${instance.id}"><spring:message code="instance.execute"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
<input type="button" name="create" value="<spring:message code="instance.create_new"/>"
       onclick="window.location='createNewInstance'"/>
</body>
</html>