package ec.diners.com.infrastructure.repositories.security.userauthtoken;

import ec.diners.com.infrastructure.modelsDb.security.UserAuthTokenDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface JpaUserAuthTokenRepository extends JpaRepository<UserAuthTokenDbModel, UUID>, JpaSpecificationExecutor<UserAuthTokenDbModel> {
}
