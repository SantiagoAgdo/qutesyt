package ec.diners.com.domain.interfaces.repositories.segment;


import ec.diners.com.domain.entities.segment.Segment;

import java.util.List;

public interface ISegmentRepository {

    Segment findByCodeSegment(Integer codeSegment);

    Segment findById(Long id);

    List<Segment> getAll();

}
