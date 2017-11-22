<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript">
        function cancel() {
            window.location.href = '/employeeById?id=${idList}';
        }
    </script>
    <script src="/validate.js"></script>

    <title>${pageTitle}</title>
</head>
<body>

<H1>${pageTitle}</H1><div id="Error"></div>
<br>
<br>
<form name="employee"
      onsubmit="return validateFormEmployee()" method="POST">
    <table>
        <tr>
            <td width="100">Name</td>
            <td width="170">
                <%--<input type="hidden" name="id" value="${employee.id}">--%>
                <input name="name" value="${employee.name}"/>
            </td>
            <td width="200">
                <div id="NameError"></div>
            </td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input name="surname" value="${employee.surname}"/></td>
            <td width="200">
                <div id="SurnameError"></div>
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input name="email" value="${employee.email}"/></td>
            <td width="200">
                <div id="EmailError"></div>
            </td>
        </tr>
        <tr>
            <td>Date</td>
            <td><input name="date" value="${employee.date}" placeholder="YYYY-MM-DD"/></td>
            <td width="200">
                <div id="DateError"></div>
            </td>
        </tr>
        <tr>
            <td><input type="button" onclick="cancel()" value="Cancel"/></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form>
</body>
</html>
