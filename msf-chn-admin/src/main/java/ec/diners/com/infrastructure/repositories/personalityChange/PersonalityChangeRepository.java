package ec.diners.com.infrastructure.repositories.personalityChange;

import ec.diners.com.domain.interfaces.repositories.personalityChange.IPersonalityChangeRepository;
import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationChangeModel;
import ec.diners.com.infrastructure.utils.TaskPersonalityStatusEnum;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Entity for save changes assignment product-theme
 * @author opinno
 * @version 1.0
 */
@Repository
@AllArgsConstructor
public class PersonalityChangeRepository implements IPersonalityChangeRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonalityChangeRepository.class);

    private final JpaPersonalityChangeRepository jpaPersonalityChangeRepository;

    @Override
    public Long save(PersonalizationChangeModel personalizationChangeModel) {
        PersonalizationChangeModel jobStateModelFinal = this.jpaPersonalityChangeRepository.save(personalizationChangeModel);
        return jobStateModelFinal.getId();
    }

    @Override
    public void deleteByName(String name) {
        this.jpaPersonalityChangeRepository.deleteByName(name);
    }

    @Override

    public void deleteById(Long id) {
        this.jpaPersonalityChangeRepository.deleteById(id);
    }

    @Override
    public PersonalizationChangeModel findStateByName(String name) {
        List<PersonalizationChangeModel> findByCode = null;
        findByCode = this.jpaPersonalityChangeRepository.findByName(name);
        return CollectionUtils.isNotEmpty(findByCode)?CollectionUtils.get(findByCode, 0):null;
    }

    @Override
    public List<PersonalizationChangeModel> findByNameAndState(List<String> names, String state) {
        List<PersonalizationChangeModel> findByNameAndState = null;
        findByNameAndState = this.jpaPersonalityChangeRepository.findIdsByNameInAndState(names, state);
        return CollectionUtils.isNotEmpty(findByNameAndState)?findByNameAndState:null;
    }

    @Override
    public List<PersonalizationChangeModel> findAllByInStates(List<String> states) {
        List<PersonalizationChangeModel> findAllByState = null;
        findAllByState = this.jpaPersonalityChangeRepository.findByStateIn(states);
        return CollectionUtils.isNotEmpty(findAllByState)?findAllByState:null;
    }

    public Boolean isAnyRunningJob(){
        return this.jpaPersonalityChangeRepository.existsByState(TaskPersonalityStatusEnum.IN_PROGRESS.getValue());
    }

}
