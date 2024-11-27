package ec.diners.com.infrastructure.repositories.rol.contract;

import ec.diners.com.infrastructure.modelsDb.rol.Functionality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFuncionalityRepository extends JpaRepository<Functionality, Long> {
}
