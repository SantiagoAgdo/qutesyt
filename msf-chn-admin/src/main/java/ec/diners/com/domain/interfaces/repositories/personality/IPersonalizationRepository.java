package ec.diners.com.domain.interfaces.repositories.personality;

import ec.diners.com.domain.entities.personality.PersonalizationDto;
import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonalizationRepository {

    void save(PersonalizationDto personalizationDto);
    void saveAll(List<PersonalizationDto> personalizationDtos);
    void saveAllNative(List<PersonalizationModel> lstPersonalizationHomeModels);
    PersonalizationDto findById(String dinersId);
    List<PersonalizationDto> findAllByDinersId(List<String> ids);
    void delleteAll();
    Iterable<PersonalizationModel> findAll();
    Page<PersonalizationModel> findPersonalizationHomeModelByPage(Pageable pageable);

}
