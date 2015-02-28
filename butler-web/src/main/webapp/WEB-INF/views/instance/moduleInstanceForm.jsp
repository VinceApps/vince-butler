<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <c:if test="${moduleInstance.id == null}"><spring:message code="instance.create_new"/></c:if>
        <c:if test="${moduleInstance.id != null}"><spring:message code="instance.edit"/></c:if>
    </title>
</head>
<body>
<h1>
    <c:if test="${moduleInstance.id == null}"><spring:message code="instance.create_new"/></c:if>
    <c:if test="${moduleInstance.id != null && !editMode}"><spring:message code="instance.view"/></c:if>
    <c:if test="${moduleInstance.id != null && editMode}"><spring:message code="instance.edit"/></c:if>
</h1>
<form:form method="POST" commandName="moduleInstance" action="storeInstance ">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td><form:label path="name"><spring:message code="instance.name"/></form:label></td>
            <td><form:input path="name" disabled="${!editMode}"/></td>
            <td class="error"><form:errors path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="module"><spring:message code="instance.module"/></form:label></td>
            <td>
                <form:select path="module" disabled="${!editMode}">
                    <form:option value=""><spring:message code="instance.select_module"/></form:option>
                    <form:options items="${modules}" itemLabel="name" itemValue="name"/>
                </form:select>
            </td>
            <td class="error"><form:errors path="module"/></td>
        </tr>
        <c:if test="${moduleInstance.module.parameters.size() > 0}">
            <tr>
                <td colspan="3"><spring:message code="instance.parameters"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <table>
                        <thead>
                        <tr>
                            <td><spring:message code="instance.parameter_name"/></td>
                            <td><spring:message code="instance.parameter_type"/></td>
                            <td><spring:message code="instance.parameter_value"/></td>
                            <td></td>
                        </tr>
                        </thead>
                        <c:forEach var="parameter" items="${moduleInstance.module.parameters}">
                            <tr>
                                <td>${parameter.parameterName}</td>
                                <td>${parameter.parameterType}</td>
                                <td><form:input path="parameterValues[${parameter.parameterName}]"
                                                disabled="${!editMode}"/></td>
                                <td class="error"><form:errors path="parameterValues[${parameter.parameterName}]"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </c:if>
    </table>
    <c:if test="${!editMode}">
        <td>
            <input type="button" name="close" value="<spring:message code="instance.close"/>"
                   onclick="window.location='listAllInstances'"/>
        </td>
        <td>
            <input type="button" name="close" value="<spring:message code="instance.edit"/>"
                   onclick="window.location='editInstance?id='+${moduleInstance.id}"/>
        </td>
    </c:if>
    <c:if test="${editMode}">
        <td><input type="button" name="cancel" value="<spring:message code="instance.cancel"/>"
                   onclick="window.location='listAllInstances'"/></td>
        <td><input type="submit" name="save" value="<spring:message code="instance.save"/>"/></td>
    </c:if>

</form:form>
</body>
</html>