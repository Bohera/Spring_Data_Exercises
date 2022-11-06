import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String departmentName = "Research and Development";

        List<Employee> resultList = entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.department.name = :dn ORDER BY e.salary, e.id", Employee.class)
                .setParameter("dn", departmentName)
                .getResultList();

        for (Employee employee : resultList) {
            System.out.printf("%s %s from %s - $%.2f%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        }
        entityManager.close();
    }
}
