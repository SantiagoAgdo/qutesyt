package ec.diners.com.domain.interfaces.repositories.rol;

import ec.diners.com.domain.entities.rol.FunctionalityDto;

public interface IFuncionalityRepository {

    FunctionalityDto findById(Long id);
}
