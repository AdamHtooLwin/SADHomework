<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Human Resources</title>
</head>

<style>
    table, th, td {
  border: 1px solid black;
}
</style>

<body>
    <h1>Employees</h1>
    <hr>

    <table>
        <tr>
            <th>Employee Name</th>
            <th>Address</th>
            <th>Position Level</th>
            <th>Net value</th>
        </tr>
        
        <c:forEach items="${result}" var="employeedata">
            <c:choose>
                <c:when test="${employeedata.netValue le 0}">
                    <tr style="background-color: red;">
                        <td><c:out value="${employeedata.employee.name}"/></td>
                        <td><c:out value="${employeedata.employee.address}"/></td>
                        <td><c:out value="${employeedata.employee.positionLevel}"/></td>
                        <td><c:out value="${employeedata.netValue}"/></td>  
                        <td>
                            <form action="edit.jsp?id=${employeedata.employee.id}" method="GET">
                                <input type="submit" value="Edit">
                            </form>
                        </td>
                        <td>
                            <form action="employees/delete/${employeedata.employee.id}" method="GET">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td><c:out value="${employeedata.employee.name}"/></td>
                        <td><c:out value="${employeedata.employee.address}"/></td>
                        <td><c:out value="${employeedata.employee.positionLevel}"/></td>
                        <td><c:out value="${employeedata.netValue}"/></td> 
                        <td>
                            <form action="edit.jsp?id=${employeedata.employee.id}" method="GET">
                                <input type="submit" value="Edit">
                            </form>
                        </td>
                        <td>
                            <form action="employees/delete/${employeedata.employee.id}" method="GET">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>
    <hr>

</body>
</html>