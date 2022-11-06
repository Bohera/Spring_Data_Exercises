import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Project> last10Projects = entityManager
                .createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        List<Project> sortedLast10Projects = last10Projects
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());

        for (Project project : sortedLast10Projects) {
            System.out.printf("Project name: %s%n   Project Description: %s%n   Project Start Date:%s%n   Project End Date: %s%n",
                    project.getName(),
                    project.getDescription(),
                    project.getStartDate(),
                    project.getEndDate());
        }
    }
}
