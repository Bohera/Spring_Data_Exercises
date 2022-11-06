import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        List<Department> departments = entityManager
                .createQuery("SELECT d FROM Department AS d", Department.class)
                .getResultList();

        for (Department department : departments) {
            Employee maxSalaryEmployee = entityManager
                    .createQuery("SELECT e FROM Employee AS e WHERE e.department.name = :dn AND e.salary = (SELECT MAX(e2.salary) FROM Employee AS e2 WHERE e2.department.name = :dn)", Employee.class)
                    .setParameter("dn", department.getName())
                    .setMaxResults(1)
                    .getSingleResult();
            if (maxSalaryEmployee.getSalary().longValue() < 30000 || maxSalaryEmployee.getSalary().longValue() > 70000) {
                System.out.printf("%s %.2f%n", department.getName(), maxSalaryEmployee.getSalary());
            }
        }

    }
}
