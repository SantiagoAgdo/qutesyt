package ec.diners.com.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "CHAR(36)")
    protected UUID id;

    @Column(name = "is_active", nullable = false)
    protected boolean active = true;

    @Column(name = "update_at")
    @UpdateTimestamp
    protected Date updateAt;

    @Column(name = "delete_at")
    protected Date deleteAt;

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "updated_by")
    protected String updatedBy;

    @Column(name = "deleted_by")
    protected String deletedBy;

    public BaseEntity() {
        this.active = true;
    }

    public BaseEntity(UUID id) {
        this.id = id;
        this.active = true;
    }

    public BaseEntity(UUID id, boolean active, Date updateAt, Date deleteAt) {
        this.id = id;
        this.active = active;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
