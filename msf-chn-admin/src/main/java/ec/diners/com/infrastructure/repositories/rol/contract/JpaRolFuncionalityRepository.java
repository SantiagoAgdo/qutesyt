package ec.diners.com.infrastructure.repositories.rol.contract;

import ec.diners.com.infrastructure.modelsDb.rol.RoleFunctionality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRolFuncionalityRepository extends JpaRepository<RoleFunctionality, Long> {
}
