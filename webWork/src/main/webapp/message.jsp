<%--
  Created by IntelliJ IDEA.
  User: Elat
  Date: 19.04.2017
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>

<H1>${message}</H1>
<br/>
<div id="departmentName">${depName}</div>
<div id="employeeName">${empName}</div>
<div id="surname">${empSurname}</div>
<div id="email">${empEmail}</div>
<div id="date">${empDate}</div>
<div id="empId">${empId}</div>
<div id="depNumber">${depNumber}</div>
<br/>
<button onclick="goBack()">Go Back</button>
<script language="JavaScript">
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>
