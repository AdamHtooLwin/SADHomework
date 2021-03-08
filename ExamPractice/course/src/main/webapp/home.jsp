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
  <title>Courses</title>
</head>
    <div class="container">
    <br>
    <h1>Enroll in courses</h1>
    <hr>
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th>ID #</th>
                <th>Title</th>
                <th>Date Offered</th>
                <th>Discipline</th>
                <th>Course Type</th>
                <th>Revenue Generated</th>
                <th>Enrolled</th>
                <th>Enrollment</th>
            </tr>
        </thead>
        
        <tbody>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <th scope="row"><c:out value="${course.id}"/></th></th>
                    <td><c:out value="${course.name}"/></td>
                    <td><c:out value="${course.dateOffered}"/></td>
                    <td><c:out value="${course.discipline}"/></td>
                    <td><c:out value="${course.type}"/></td>
                    <td><c:out value="${course.revenueGenerated}"/></td>
                    <td>
                        <c:forEach items="${user.courses}" var="user_course">
                            <c:if test="${user_course.id eq course.id}">
                                <p>Enrolled</p>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <form action="course/${course.id}/enroll" method="GET">
                            <input type="submit" value="Enroll">
                        </form>
                    </td>

                    <!-- <td>
                        <c:forEach items="${user.courses}" var="user_course">
                            <p><c:out value="${user_course.name}"/></p>
                        </c:forEach>
                    </td> -->
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>

    <a href="addCourse"><h1>Add Course</h1></a>
    <hr>
    </div>
</html>