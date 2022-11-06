import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String[] names = scanner.nextLine().split(" ");

        String firstName = names[0];
        String lastName = names[1];


        Long countOfMatches = entityManager.createQuery(
                        "SELECT COUNT(e) FROM Employee As e WHERE e.firstName = :fn AND e.lastName = :ln", Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();

        if(countOfMatches == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        entityManager.close();
    }
}
