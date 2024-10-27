<!DOCTYPE html>
<html>
<head>
    <title>Add Task</title>
</head>
<body>
<h1>Add a New Task</h1>

<form action="${pageContext.request.contextPath}/addTask" method="post">
    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" required><br><br>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description" required><br><br>

    <label for="status">Status:</label><br>
    <select id="status" name="status">
        <option value="To-Do">To-Do</option>
        <option value="In-Progress">In-Progress</option>
        <option value="Completed">Completed</option>
    </select><br><br>

    <label for="dueDate">Due Date:</label><br>
    <input type="date" id="dueDate" name="dueDate" required><br><br>

    <label for="userId">User ID:</label><br>
    <input type="number" id="userId" name="userId" required><br><br>

    <input type="submit" value="Add Task">
</form>
</body>
</html>
