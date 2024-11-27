package ec.diners.com.infrastructure.repositories.user;

import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.domain.interfaces.user.IUserRepository;
import ec.diners.com.infrastructure.modelsDb.user.UserModel;
import ec.diners.com.infrastructure.repositories.user.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements IUserRepository {

    private final JpaUsersRepository repository;
    private final UserMapper mapper;

    public UserRepository(JpaUsersRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public String save(UserDto userDto) {
        UserModel userModel = mapper.toUserModel(userDto);
        UserModel savedUser = repository.save(userModel);
        return savedUser.getId().toString();
    }

    @Override
    public List<UserDto> getAll() {
        List<UserModel> users = repository.findAll();
        return users.stream()
                .map(mapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public void update(UserDto userDto) {
        if (userDto == null || userDto.getId() == null) {
            throw new IllegalArgumentException("User ID must not be null for update operation");
        }

        Long longId = Long.valueOf(userDto.getId());
        UserModel existingUser = repository.findById(longId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getId()));

        existingUser.setName(userDto.getName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setIdentificationNumber(userDto.getIdentificationNumber());
        existingUser.setIdentificationType(userDto.getIdentificationType());
        existingUser.setTelephoneNumber(userDto.getTelephoneNumber());
        existingUser.setPhoto(userDto.getPhoto());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPwd(userDto.getPwd());
        existingUser.setToken(userDto.getToken());
        existingUser.setEnabled(userDto.isEnabled());

        repository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toUser);
    }

    @Override
    public Optional<UserDto> findByEmailAndPwd(String email, String pwd) {
        return repository.findByEmailAndPwd(email, pwd)
                .map(mapper::toUser);
    }
}
