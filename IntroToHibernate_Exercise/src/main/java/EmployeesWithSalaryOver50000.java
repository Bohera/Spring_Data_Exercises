import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<String> resultList = entityManager
                .createQuery("SELECT firstName FROM Employee WHERE salary > 50000")
                .getResultList();

        for (String name : resultList) {
            System.out.println(name);
        }

        entityManager.close();
    }
}
