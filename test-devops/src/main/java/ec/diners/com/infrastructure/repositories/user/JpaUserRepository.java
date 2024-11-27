package ec.diners.com.infrastructure.repositories.user;

import ec.diners.com.infrastructure.modelsDb.user.UserDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface JpaUserRepository extends JpaRepository<UserDbModel, UUID>, JpaSpecificationExecutor<UserDbModel> {
    UserDbModel findByEmailAndIsActive(String email, boolean isActive);
}
