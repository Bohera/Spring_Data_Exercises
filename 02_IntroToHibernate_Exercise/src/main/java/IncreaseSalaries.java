import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Employee> selectedEmployees = entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.department.name in ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();

        entityManager.getTransaction().begin();

        for (Employee employee : selectedEmployees) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
                            System.out.printf("%s %s ($%.2f)%n",
                                    employee.getFirstName(),
                                    employee.getLastName(),
                                    employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
