package com.mytaskapp.mytaskapp;

import entities.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo_list");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        Task task = em.find(Task.class, taskId);
        if (task != null) {
            em.remove(task);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();

        response.sendRedirect("showTask");
    }
}
