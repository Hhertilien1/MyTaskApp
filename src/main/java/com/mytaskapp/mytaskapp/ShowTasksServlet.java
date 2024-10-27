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
import java.util.List;

@WebServlet("/showTasks")
public class ShowTasksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo_list");
        EntityManager em = emf.createEntityManager();

        List<Task> tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        request.setAttribute("tasks", tasks);

        em.close();
        emf.close();

        request.getRequestDispatcher("showTask").forward(request, response);
    }
}
