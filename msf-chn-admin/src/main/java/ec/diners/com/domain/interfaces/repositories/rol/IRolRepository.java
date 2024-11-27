package ec.diners.com.domain.interfaces.repositories.rol;

import ec.diners.com.domain.entities.rol.RoleDto;

import java.util.List;

public interface IRolRepository {

    RoleDto findById(String id);
    String save(RoleDto roleDto);
    List<RoleDto> getAll();
    void update(RoleDto roleDto);
    void delete(String id);
}
