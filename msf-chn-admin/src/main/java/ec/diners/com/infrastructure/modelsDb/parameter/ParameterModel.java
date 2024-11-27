package ec.diners.com.infrastructure.modelsDb.parameter;

import ec.diners.com.infrastructure.modelsDb.AbstractBaseAuditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parameter")
@Getter
@Setter
public class ParameterModel extends AbstractBaseAuditable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "root_id")
    private Long rootId;

    @Column(name = "name")
    private String name;

    @Column(name = "value_param")
    private String value;
}
