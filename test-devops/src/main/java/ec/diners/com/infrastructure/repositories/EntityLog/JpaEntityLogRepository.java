package ec.diners.com.infrastructure.repositories.EntityLog;

import ec.diners.com.infrastructure.modelsDb.logs.EntityLogDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface JpaEntityLogRepository extends JpaRepository<EntityLogDbModel, UUID>, JpaSpecificationExecutor<EntityLogDbModel> {
}
