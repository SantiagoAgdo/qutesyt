package ec.diners.com.infrastructure.modelsDb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

/**
 * Abstract auditable
 * @author opinno
 * @version 1.0
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class })
public class AbstractBaseAuditable implements Serializable {

	/**
     * True if the record is active otherwise false
     */
    @Column(name = "is_active", columnDefinition = "tinyint default 1")
    protected Boolean isActive;

    /**
     * Date of the last modification
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    protected Date updatedAt;

    /**
     * Date of the last modification
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    protected Date createdAt;

    /**
     * User who created the record
     */
    @CreatedBy
    @Column(name = "creator_user_id", updatable = false)
    protected String creatorUserId;

    /**
     * User who made the last modification of the registry
     */
    @LastModifiedBy
    @Column(name = "updater_user_id")
    protected String updaterUserId;
}
