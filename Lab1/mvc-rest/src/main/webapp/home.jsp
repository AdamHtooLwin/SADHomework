
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add users</title>
</head>
<body>

    <label>Add User</label>

    <!-- http:localhost:8080/addUser?uid=&name=&nationality-->
    <form action="addUser">
        UID: <input type="text" name="uid"><br>
        Name: <input type="text" name="name"><br>
        Nationality: <input type="text" name="nationality"><br>
        <input type="submit">
    </form>

    <!-- http://localhost:8080/getUser?uid= -->
    <label>Get User by specifying ID</label>
    <form action="getUser">
        UID: <input type="text" name="uid"><br>
        <input type="submit">
    </form>

</body>
</html>