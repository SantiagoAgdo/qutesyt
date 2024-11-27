package ec.diners.com.infrastructure.modelsDb.rol;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "Functionality")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Functionality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "createdAt", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updatedAt")
    @CreationTimestamp
    private Date updatedAt;
}
