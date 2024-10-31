<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://jakarta.apache.org/taglibs/standard/jsp" prefix="c" %>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>To-Do List</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            // Load tasks on page load
            loadTasks();

            // Function to load tasks from the server
            function loadTasks() {
                $.get("TaskServlet", function(data) {
                    $("#task-list").html(data);
                });
            }

            // Add a new task
            $("#add-task-btn").click(function() {
                var taskName = $("#new-task-name").val();
                if (taskName) {
                    $.post("TaskServlet", { action: "add", taskName: taskName }, function() {
                        loadTasks(); // Reload tasks after adding
                        $("#new-task-name").val(""); // Clear the input
                    });
                } else {
                    alert("Please enter a task name.");
                }
            });

            // Delete a task
            $(document).on("click", ".delete-task-btn", function() {
                var taskId = $(this).data("id");
                if (confirm("Are you sure you want to delete this task?")) {
                    $.post("TaskServlet", { action: "delete", taskId: taskId }, function() {
                        loadTasks(); // Reload tasks after deletion
                    });
                }
            });
        });
    </script>
</head>
<body>

<h1>To-Do List</h1>

<div>
    <input type="text" id="new-task-name" placeholder="New Task Name">
    <button id="add-task-btn">Add Task</button>
</div>

<h2>Your Tasks</h2>
<div id="task-list">
    <!-- Task list will be loaded here via AJAX -->
</div>

</body>
</html>
