import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        String townName = new Scanner(System.in).nextLine();

        Town town = entityManager.createQuery("SELECT t FROM Town AS t WHERE t.name = :tn", Town.class)
                .setParameter("tn", townName)
                .getSingleResult();

        List<Address> addresses = entityManager
                .createQuery("SELECT a FROM Address AS a WHERE a.town.name = :tn", Address.class)
                .setParameter("tn", townName)
                .getResultList();

        entityManager.getTransaction().begin();

        addresses.forEach(address -> {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);
            }
            address.setTown(null);
            entityManager.remove(address);
        });

        entityManager.remove(town);

        entityManager.getTransaction().commit();

        System.out.printf("%d address%s in %s deleted%n",
                addresses.size(),
                addresses.size() != 1 ? "es" : "",
                town.getName());

        entityManager.close();
    }
}
