package ec.diners.com.domain.interfaces.repositories.user;

import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.interfaces.repositories.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
}
