<%--
  Created by IntelliJ IDEA.
  User: Elat
  Date: 08.04.2017
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript">
    function toDep() {
        window.location.href = '/departmentsList';
    }
</script>
<head>
    <title>AllEmployees</title>

</head>
<body>
<H1>AllEmployees</H1>
<br>
<br>
<div id="noEmp">${noEmp}</div>
<form name="myForm">
    <table border="1px">
        <tr>
            <td width="30">#</td>
            <%--<td width="20">ID</td>--%>
            <td width="200">Name</td>
            <td width="150">Surname</td>
            <td width="200">Email</td>
            <td width="40">idDept</td>
            <td width="40">Date</td>
            <td width="40">Action1</td>
            <td width="40">Action2</td>

        </tr>
        <c:forEach items="${employees}" var="employee" varStatus="status" >
            <tr>
                <td>${status.index + 1}</td>
                    <%--<td>${employee.id}</td>--%>
                <td>${employee.name}</td>
                <td>${employee.surname}</td>
                <td>${employee.email}</td>
                <td>${employee.depId}</td>
                <td>${employee.date}</td>
                <td><a href="/employeeEdit?id=${employee.id}">Edit</a></td>
                <td><a href="/employeeDelete?id=${employee.id}"> Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</form>
<input type="button"  onclick="toDep()" value="To departments list"/>
</body>
</html>

