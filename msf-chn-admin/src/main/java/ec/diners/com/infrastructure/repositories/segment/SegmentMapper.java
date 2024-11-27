package ec.diners.com.infrastructure.repositories.segment;

import ec.diners.com.domain.entities.segment.Segment;
import ec.diners.com.infrastructure.modelsDb.theme.SegmentModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SegmentMapper {

    @Mapping(source="id", target="id")
    @Mapping(source="codeSegment", target="codeSegment")
    @Mapping(source="name", target="name")
    Segment toSegment(SegmentModel model);

    List<Segment> toSegment(List<SegmentModel> SegmentModel);
    @InheritInverseConfiguration
    SegmentModel toSegmentModel(Segment segment);
}
