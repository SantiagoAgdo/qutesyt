package ec.diners.com.infrastructure.modelsDb.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "last_name", length = 128)
    private String lastName;

    @Column(name = "identification_number", length = 60)
    private String identificationNumber;

    @Column(name = "identification_type", length = 60)
    private String identificationType;

    @Column(name = "telephone_number", length = 20)
    private String telephoneNumber;

    @Column(name = "photo", length = 60)
    private String photo;

    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;

    @Column(name = "pwd", length = 256, nullable = false)
    private String pwd;

    @Column(name = "token", length = 256)
    private String token;

    @Column(name = "enabled", length = 20)
    private boolean enabled;
}

