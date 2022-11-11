package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @Column
    private int id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Column(unique = true, length = 30)
    private String email;

    @Column(nullable = false, length = 25)
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<BillingDetail> billingDetails;

}
