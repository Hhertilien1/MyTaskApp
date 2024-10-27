<!DOCTYPE html>
<html>
<head>
    <title>Task List</title>
</head>
<body>
<h1>Task List</h1>

<form action="${pageContext.request.contextPath}/deleteTask" method="post">

<table border="1">
    <tr>
        <th>Task ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Status</th>
        <th>Due Date</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="task" items="${taskList}">
        <tr>
            <td>${task.taskId}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>${task.status}</td>
            <td>${task.dueDate}</td>
            <td><a href="deleteTask?taskId=${task.taskId}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="addTask">Add a new task</a>
</body>
</html>
