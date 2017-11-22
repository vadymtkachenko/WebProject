<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${pageTitle}</title>

    <script type="text/javascript">
        function cancel() {
            window.location.href = '/departmentsList';
        }
    </script>

    <script src="/validate.js"></script>
</head>
<body>

<H1>${pageTitle}</H1><div id="Error"></div>
<br>
<br>
<form name="department" onsubmit="return validateFormDep()" method="POST">
    <table>
        <tr>
            <td width="100">Name</td>
            <td width="200">
                <input type="hidden" name="id" value="${department.id}">
                <input name="name" value="${department.name}"/>
            </td>
            <td width="200">
                <div id="NameError"></div>
            </td>
        </tr>
        <tr>
            <td>Number</td>
            <td><input name="number" value="${department.number}"/></td>
            <td width="200">
                <div id="NumberError"></div>
            </td>
        </tr>
        <tr>
            <td><input type="button"  onclick="cancel()" value="Cancel"/></td>
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form>
</body>
</html>
