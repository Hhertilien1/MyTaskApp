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
import java.util.List;
import entities.Task;

@WebServlet("/showTask")
public class ShowTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get tasks from the database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo_list");
        EntityManager em = emf.createEntityManager();

        try {
            // Query all tasks
            List<Task> taskList = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();

            // Set tasks as a request attribute
            request.setAttribute("taskList", taskList);
        } finally {
            em.close();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/showTask");
        dispatcher.forward(request, response);
    }
}
