package com.mytaskapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import entities.Task;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward to the addTask.jsp page to display the task creation form
        request.getRequestDispatcher("addTask").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from the form
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String dueDateStr = request.getParameter("dueDate");
        int userId = Integer.parseInt(request.getParameter("userId"));

        Date dueDate = null;
        try {
            // Parse date input
            dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a new task entity
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setStatus(status);
        newTask.setDueDate(dueDate);

        // Persist the new task to the database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo_list");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(newTask);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        // Redirect back to the showTask page after adding the task
        RequestDispatcher dispatcher = request.getRequestDispatcher("/showTask.jsp");
        dispatcher.forward(request, response);
    }
}
