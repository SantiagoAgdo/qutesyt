package ec.diners.com.infrastructure.repositories.rol.impl;

import ec.diners.com.domain.entities.rol.RoleDto;
import ec.diners.com.domain.interfaces.repositories.rol.IRolRepository;
import ec.diners.com.infrastructure.modelsDb.rol.RoleModel;
import ec.diners.com.infrastructure.repositories.rol.contract.JpaRolRepository;
import ec.diners.com.infrastructure.repositories.rol.mapper.RolMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RolRepository implements IRolRepository {

    private final JpaRolRepository repository;

    private final RolMapper mapper;

    public RolRepository(JpaRolRepository repository, RolMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RoleDto findById(String id) {
        Long longId = Long.valueOf(id);
        return repository.findById(longId)
                .map(mapper::toRole)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
    }

    @Override
    public String save(RoleDto roleDto) {
        RoleModel roleModel = mapper.toRoleModel(roleDto);
        RoleModel savedRole = repository.save(roleModel);
        return savedRole.getId().toString();
    }

    @Override
    public List<RoleDto> getAll() {
        List<RoleModel> roles = repository.findAll();
        return roles.stream()
                .map(mapper::toRole)
                .collect(Collectors.toList());
    }

    @Override
    public void update(RoleDto roleDto) {
        if (roleDto == null || roleDto.getRoleId() == null) {
            throw new IllegalArgumentException("Role ID must not be null for update operation");
        }

        Long longId = Long.valueOf(roleDto.getRoleId());
        RoleModel existingRole = repository.findById(longId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleDto.getRoleId()));

        existingRole.setName(roleDto.getName());
        existingRole.setDescription(roleDto.getDescription());
        existingRole.setEnabled(roleDto.isEnabled());

        repository.save(existingRole);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(Long.valueOf(id));
    }
}
