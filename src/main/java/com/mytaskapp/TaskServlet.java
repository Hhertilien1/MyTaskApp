package servlets;

import entities.Task;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        // Mock user ID for demonstration, replace with actual user session ID
        Long userId = 1L;
        User user = em.find(User.class, userId);

        // Fetch tasks associated with the user
        List<Task> tasks = em.createQuery("SELECT t FROM Task t WHERE t.user = :user", Task.class)
                .setParameter("user", user)
                .getResultList();

        // Output task list in HTML format for AJAX
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        for (Task task : tasks) {
            out.println("<div>");
            out.println("<span>" + task.getTitle() + " - " + task.getStatus() + "</span>");
            out.println("<button class='delete-task-btn' data-id='" + task.getTaskId() + "'>Delete</button>");
            out.println("</div>");
        }

        em.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        EntityManager em = emf.createEntityManager();

        // Mock user ID for demonstration, replace with actual user session ID
        Long userId = 1L;
        User user = em.find(User.class, userId);

        if ("add".equals(action)) {
            String taskName = request.getParameter("taskName");

            // Add a new task
            em.getTransaction().begin();
            Task task = new Task(user, taskName, "", "To-Do", null); // Default status: To-Do
            em.persist(task);
            em.getTransaction().commit();
        } else if ("delete".equals(action)) {
            Long taskId = Long.parseLong(request.getParameter("taskId"));

            // Delete task
            em.getTransaction().begin();
            Task task = em.find(Task.class, taskId);
            if (task != null && task.getUser().getUserId().equals(userId)) {
                em.remove(task);
            }
            em.getTransaction().commit();
        }

        em.close();
    }

    @Override
    public void destroy() {
        emf.close();
    }
}
