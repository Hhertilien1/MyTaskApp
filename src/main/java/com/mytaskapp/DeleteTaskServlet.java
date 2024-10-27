package com.mytaskapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.IOException;
import entities.Task;

@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int taskId = Integer.parseInt(request.getParameter("taskId"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo_list");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Find and remove the task
            Task task = em.find(Task.class, taskId);
            if (task != null) {
                em.remove(task);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        // Redirect to showTask page after deletion
        response.sendRedirect("showTasks");
    }
}
