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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo_list");
        EntityManager em = emf.createEntityManager();

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
            e.printStackTrace();
        }

        Task task = new Task(user, "Code", "Finish the JPA assignment by tomorrow.", "In Progress", dueDate);
        em.persist(task);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
