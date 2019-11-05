<%@page language="java" contentType="text/html" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Index</title>
</head>
<body>
<h1>Hello world We Are Going to Start Our Project</h1>
<h1> We are gonna Rock!!</h1>
<form action="/courses" method="post">
    <input type="text" name="title"/>
    <button type="submit">Submit</button>
</form>
<c:forEach items="${courses}" var="course">
    <c:out value="Course ID : ${course.courseID}"/>
    <c:out value="Course Name : ${course.courseTitle}"/>
</c:forEach>
</body>
</html>