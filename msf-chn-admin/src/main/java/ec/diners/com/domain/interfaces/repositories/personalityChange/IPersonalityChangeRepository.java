package ec.diners.com.domain.interfaces.repositories.personalityChange;

import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationChangeModel;

import java.util.List;

/**
 * Interface for IPersonalityChangeRepository
 * @author opinno
 * @version 1.0
 */
public interface IPersonalityChangeRepository {

    Long save(PersonalizationChangeModel personalizationChangeModel);
    void deleteByName(String name);
    void deleteById(Long id);
    PersonalizationChangeModel findStateByName(String name);
    List<PersonalizationChangeModel> findByNameAndState(List<String> names, String state);
    List<PersonalizationChangeModel> findAllByInStates(List<String> states);

}
