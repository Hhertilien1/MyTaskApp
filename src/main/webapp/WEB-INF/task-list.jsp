<%--
  Created by IntelliJ IDEA.
  User: Herby
  Date: 10/30/2024
  Time: 6:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>

<jsp:useBean id="tasks" scope="request" type="java.util.List"/>
<c:forEach var="task" items="${tasks}">
    <div>
        <span>${task.name}</span>
        <button class="edit-task-btn" data-id="${task.id}">Edit</button>
        <button class="delete-task-btn" data-id="${task.id}">Delete</button>
    </div>
</c:forEach>
