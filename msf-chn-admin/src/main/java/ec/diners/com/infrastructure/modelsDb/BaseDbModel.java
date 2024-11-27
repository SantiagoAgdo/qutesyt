package ec.diners.com.infrastructure.modelsDb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Component
@Getter
@Setter
public abstract class BaseDbModel {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "is_active", nullable = false)
    protected boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date createAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updateAt;

    protected Date deleteAt;

    @Column(length = 64)
    protected String createdBy;

    @Column(length = 64)
    protected String updatedBy;

    @Column(length = 64)
    protected String deletedBy;

    public BaseDbModel() {
    }

    public BaseDbModel(UUID id) {
        this.id = id;
        this.isActive = true;
        this.createAt = new Date();
        this.updateAt = new Date();
        this.deleteAt = null;
    }

    public BaseDbModel(UUID id, boolean isActive, Date createAt, Date updateAt, Date deleteAt) {
        this.id = id;
        this.isActive = isActive;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    public Long getId() {
        return id;
    }
}


