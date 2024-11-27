package ec.diners.com.domain.entities;

import java.util.Date;
import java.util.UUID;

public class BaseEntity {
    protected UUID id;
    protected boolean active;
    protected Date createAt;
    protected Date updateAt;
    protected Date deleteAt;

    protected String createdBy;
    protected String updatedBy;
    protected String deletedBy;

    public BaseEntity() {
    }

    public BaseEntity(UUID id) {
        this.id = id;
        this.active = true;
        this.createAt = new Date();
        this.updateAt = new Date();
        this.deleteAt = null;
    }

    public BaseEntity(UUID id, boolean active, Date createAt, Date updateAt, Date deleteAt) {
        this.id = id;
        this.active = active;
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
        return active;
    }

    public void setIsActive(boolean active) {
        this.active = active;
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
