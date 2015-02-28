<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="modules"/></title>
    <link rel="stylesheet" type="text/css" href="style/default.css">
</head>
<body>
<h1><spring:message code="modules.available"/></h1>
<table>
    <thead>
    <tr>
        <th><spring:message code="modules.name"/></th>
        <th><spring:message code="modules.url"/></th>
        <th><spring:message code="modules.no_of_parameters"/></th>
        <th><spring:message code="actions"/></th>
    </tr>
    </thead>
    <c:forEach var="module" items="${modules}">
        <tr>
            <td>${module.name}</td>
            <td><a href="${module.url}">${module.url}</a></td>
            <td class="number">
                <c:if test="${module.parameters == null}">0</c:if>
                    ${module.parameters.size()}
            </td>
            <td>
                <a href="createNewInstance"><spring:message code="instance.add_new"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>