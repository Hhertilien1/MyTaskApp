<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add To-Do Item</title>
</head>
<body>
<h1>Add To-Do Item</h1>
<form action="addTask" method="post">
    User ID: <input type="text" name="userId" required><br>
    Title: <input type="text" name="title" required><br>
    Description: <textarea name="description" required></textarea><br>
    Status:
    <select name="status">
        <option value="To-Do">To-Do</option>
        <option value="In-Progress">In-Progress</option>
        <option value="Completed">Completed</option>
    </select><br>
    Due Date: <input type="date" name="dueDate" required><br>
    <input type="submit" value="Add Task">
</form>
<a href="showTasks">Back to To-Do List</a>
</body>
</html>
