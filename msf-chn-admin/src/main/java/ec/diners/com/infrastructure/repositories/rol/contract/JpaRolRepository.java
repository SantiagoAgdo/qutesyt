package ec.diners.com.infrastructure.repositories.rol.contract;

import ec.diners.com.infrastructure.modelsDb.rol.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRolRepository  extends JpaRepository<RoleModel, Long> {

}
