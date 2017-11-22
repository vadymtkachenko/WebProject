<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<script type="text/javascript">
    function EmpList() {
        window.location.href = '/allEmployeesList';
    }
</script>
<head>
    <title>Departments</title>
</head>
<body>
<H1>Departments</H1>
<br>
<br>
<table border="1px">
    <tr>
        <td width="30">#</td>
        <td width="20">ID</td>
        <td width="200">Name</td>
        <td width="40">Number</td>
        <td width="60">Action 1</td>
        <td width="60">Action 2</td>
        <td width="60">Action 3</td>
    </tr>
    <c:forEach items="${departments}" var="department" varStatus="status" >
        <tr>
            <td>${status.index + 1}</td>
            <td>${department.id}</td>
            <td>${department.name}</td>
            <td>${department.number}</td>
            <td><a href="/departmentEdit?id=${department.id}">Edit</a></td>
            <td><a href="/departmentDelete?id=${department.id}">Delete</a></td>
            <td><a href="/employeeById?id=${department.id}">List</a></td>
        </tr>
    </c:forEach>
</table>
<input type="button" onclick="EmpList()" value="Employee List"/>
<br/>
<a href="/departmentAdd">
    <input type="button" value="Add Department" />
</a>
</body>
</html>
