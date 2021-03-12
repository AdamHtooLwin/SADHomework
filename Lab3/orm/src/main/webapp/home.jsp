<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <meta charset="ISO-8859-1">
  <title>User profile</title>
</head>
    <div class="container">
    <br>
    <h1>User Profile</h1>
    <hr>
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th>ID #</th>
                <th>Name</th>
                <th>Age</th>
            </tr>
        </thead>
        
        <tbody>
            <tr>
                <th scope="row"><c:out value="${user.emp.id}"/></th></th>
                <td><c:out value="${user.emp.name.fname}"/> <c:out value="${user.emp.name.lname}"/></td>
                <td><c:out value="${user.emp.age}"/></td>
        </tbody>
    </table>

        <h1>Leave</h1>
    <hr>

    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th>ID #</th>
                <th>Type</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Remarks</th>
                <th>Approved</th>
            </tr>
        </thead>
        
        <tbody>
            <c:forEach items="${user.emp.leaves}" var="leave">
                <tr>
                    
                    <td><c:out value="${leave.id}"/></td>
                    <td><c:out value="${leave.leaveType}"/></td>
                    <td><c:out value="${leave.start}"/></td>
                    <td><c:out value="${leave.end}"/></td>
                    <td><c:out value="${leave.remarks}"/></td>
                    <td><c:out value="${leave.approved}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <hr>

    <br>
	<div class="container">
        <h1>Apply for leave</h1>
            <hr>
            <form action="/leaves" method="POST">
              Start Date: <input type="date" name="start"><br>
              End Date: <input type="date" name="end"><br>
              Leave type: <select name="type" required>
                <option value="SICK">Sick</option>
                <option value="ANNUAL">Annual</option>
              </select><br>
              Remarks: <input type="text" name="remarks"><br>
              
              <input type="submit">
            </form>
    </div>

    <hr>    
    <a href="/admin"><h1>Admin Dashboard</h1></a>
    <hr>
    <a href="/logout">Logout</a>
</div>  
</html>