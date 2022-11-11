import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("bill_payment_system").createEntityManager();
    }
}
