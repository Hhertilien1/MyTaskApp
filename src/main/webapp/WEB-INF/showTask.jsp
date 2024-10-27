<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Task" %>
<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
</head>
<body>
<h1>To-Do List</h1>
<table border="1">
    <tr>
        <th>Task ID</th>
        <th>User ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Status</th>
        <th>Due Date</th>
        <th>Action</th>
    </tr>
    <%
        List<Task> tasks = (List<Task>) request.getAttribute("tasks");
        for (Task task : tasks) {
    %>
    <tr>
        <td><%= task.getTaskId() %></td>
        <td><%= task.getUser().getUserId() %></td>
        <td><%= task.getTitle() %></td>
        <td><%= task.getDescription() %></td>
        <td><%= task.getStatus() %></td>
        <td><%= task.getDueDate() %></td>
        <td><a href="deleteTask?taskId=<%= task.getTaskId() %>">Delete</a></td>
    </tr>
    <%
        }
    %>
</table>
<a href="addTask.jsp">Add New Task</a>
</body>
</html>
