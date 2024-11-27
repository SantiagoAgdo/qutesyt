package ec.diners.com.infrastructure.repositories.user;

import ec.diners.com.infrastructure.modelsDb.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUsersRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmailAndPwd(String email, String pwd);
    Optional<UserModel> findByEmail(String email);
}
