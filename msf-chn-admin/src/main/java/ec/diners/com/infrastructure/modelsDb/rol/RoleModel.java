package ec.diners.com.infrastructure.modelsDb.rol;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Role")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled")
    private Boolean enabled = Boolean.TRUE;

    @Column(name = "createdAt", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updatedAt")
    @CreationTimestamp
    private Date updatedAt;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleFunctionality> roleFunctionalities = new HashSet<>();
}

