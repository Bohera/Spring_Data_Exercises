import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;
import java.util.stream.Collectors;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int employeeId = new Scanner(System.in).nextInt();

        Employee employee = entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.id = :eid", Employee.class)
                .setParameter("eid", employeeId)
                .getSingleResult();

        System.out.printf("%s %s - %s%n%s",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle(),
                employee.getProjects()
                        .stream()
                        .map(Project::getName)
                        .sorted()
                        .collect(Collectors.joining(System.lineSeparator())));

    }
}
