package ec.diners.com.infrastructure.modelsDb.widget;

import ec.diners.com.domain.utils.UtilDates;
import ec.diners.com.infrastructure.modelsDb.BaseDbModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "widget")
@Getter
@Setter
public class WidgetDbModel extends BaseDbModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128,nullable = false)
    private String name;

    @Column(name = "order_priority", nullable = false)
    private Integer orderPriority;

    @PrePersist
    public void prePersist(){
        this.createAt = UtilDates.getNowTodayDate();
        this.setIsActive(Boolean.TRUE);
    }

    public Long getId() {
        return id;
    }
}
