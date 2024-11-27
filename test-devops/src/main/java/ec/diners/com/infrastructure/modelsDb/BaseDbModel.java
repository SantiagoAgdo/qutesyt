package ec.diners.com.infrastructure.modelsDb;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Component
public abstract class BaseDbModel {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(columnDefinition = "BIT DEFAULT 1",name = "is_active")
    protected boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date createAt;
    protected Date updateAt;
    protected Date deleteAt;
    @Column(length = 64)
    protected String createdBy;
    @Column(length = 64)
    protected String updatedBy;
    @Column(length = 64)
    protected String deletedBy;

    protected BaseDbModel() {
    }

    protected BaseDbModel(UUID id) {
        this.id = id;
        this.isActive = true;
        this.createAt = new Date();
        this.updateAt = new Date();
        this.deleteAt = null;
    }

    protected BaseDbModel(UUID id, boolean isActive, Date createAt, Date updateAt, Date deleteAt) {
        this.id = id;
        this.isActive = isActive;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
}


