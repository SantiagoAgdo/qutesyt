package ec.diners.com.infrastructure.repositories.personality;

import ec.diners.com.domain.entities.personality.PersonalizationDto;
import ec.diners.com.domain.interfaces.repositories.personality.IPersonalizationRepository;
import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PersonalizationRepository implements IPersonalizationRepository {

    private final JpaPersonalizationRepository jpaPersonalizationRepository;
    private final PersonalizationMapper mapper;

    @Override
    public void save(PersonalizationDto personalizationDto) {
        this.jpaPersonalizationRepository.save(mapper.toPersonalizationHomeModel(personalizationDto));
    }

    @Override
    public void saveAll(List<PersonalizationDto> personalizationDtos) {
        List<PersonalizationModel> entities = personalizationDtos.stream()
                .map(mapper::toPersonalizationHomeModel)
                .collect(Collectors.toList());
        this.jpaPersonalizationRepository.saveAll(entities);
    }

    @Override
    public void saveAllNative(List<PersonalizationModel> lstPersonalizationHomeModels) {
        this.jpaPersonalizationRepository.saveAll(lstPersonalizationHomeModels);
    }

    @Override
    @Transactional
    public void delleteAll() {
        this.jpaPersonalizationRepository.truncateTable();
    }

    @Override
    public Iterable<PersonalizationModel> findAll() {
        return this.jpaPersonalizationRepository.findAll();
    }

    @Override
    public Page<PersonalizationModel> findPersonalizationHomeModelByPage(Pageable pageable) {
        return this.jpaPersonalizationRepository.findAll(pageable);
    }

    @Override
    public PersonalizationDto findById(String dinersId) {
        return this.jpaPersonalizationRepository.findByDinersId(dinersId).map(this.mapper::toPersonalizationDto).orElse(null);
    }

    @Override
    public List<PersonalizationDto> findAllByDinersId(List<String> ids) {
        return this.jpaPersonalizationRepository.findByDinersIdIn(ids).map(this.mapper::toEntities).orElse(null);
    }

}
