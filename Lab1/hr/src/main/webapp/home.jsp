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
            <th>Net value</th>
        </tr>
        <c:forEach items="${result}" var="employeedata">
            <tr>
                <td><c:out value="${employeedata.employee.name}"/></td>
                <td><c:out value="${employeedata.netValue}"/></td>  
            </tr>
        </c:forEach>
    </table>

    <hr>
    <h2>Update a user:</h2>
    <form action="employees/edit/" method="POST">
        Employee ID: <input type="text" name="id"><br>
        Name: <input type="text" name="name"><br>
        Address: <input type="text" name="address"><br>
        Salary: <input type="text" name="salary"><br>
        Value: <input type="text" name="value"><br>
        Position level: <input type="text" name="position_level"><br>
        <input type="submit">
    </form>

    <br>

    <h2>Delete user by specifying ID</h2>
    <form action="employees/delete">
        UID: <input type="text" name="id"><br>
        <input type="submit">
    </form>

</body>
</html>