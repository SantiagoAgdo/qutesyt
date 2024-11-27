package ec.diners.com.domain.interfaces.user;

import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.infrastructure.modelsDb.user.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    UserDto findById(Long id);
    String save(UserDto userDto);
    List<UserDto> getAll();
    void update(UserDto userDto);
    void delete(Long id);
    Optional<UserDto> findByEmail(String email);
    Optional<UserDto> findByEmailAndPwd(String email, String pwd);

}
