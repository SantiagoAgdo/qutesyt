package ec.diners.com.infrastructure.repositories.segment;

import ec.diners.com.domain.entities.segment.Segment;
import ec.diners.com.domain.interfaces.repositories.segment.ISegmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SegmentRepository implements ISegmentRepository {

    private final JpaSegmentRepository jpaSegmentRepository;

    private final SegmentMapper mapper;

    public SegmentRepository(JpaSegmentRepository jpaSegmentRepository, SegmentMapper mapper) {
        this.jpaSegmentRepository = jpaSegmentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Segment> getAll() {
        var storeList= jpaSegmentRepository.findAll();
        return mapper.toSegment(storeList);
    }

    @Override
    public Segment findByCodeSegment(Integer codeSegment) {
        return this.jpaSegmentRepository.findByCodeSegment(codeSegment).map(this.mapper::toSegment).orElse(null);
    }

    @Override
    public Segment findById(Long id) {
        return this.jpaSegmentRepository.findById(id).map(this.mapper::toSegment).orElse(null);
    }
}
