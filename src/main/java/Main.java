import entities.Task;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("todo_list");
            em = emf.createEntityManager();

            em.getTransaction().begin();

            // Creating a new User with all fields
            User user = new User("gabbie_charles", "gabbie.charles@example.com", "securepassword1234");
            em.persist(user);

            // Creating a new Task with all fields
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dueDate = null;
            try {
                dueDate = sdf.parse("2024-10-27");
            } catch (ParseException e) {
                System.err.println("Invalid date format: " + e.getMessage());
            }

            if (dueDate != null) {
                Task task = new Task(user, "Code", "Finish the JPA assignment by tomorrow.", "In Progress", dueDate);
                em.persist(task);
            } else {
                System.err.println("Task creation failed due to invalid date.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback transaction if something goes wrong
            }
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
