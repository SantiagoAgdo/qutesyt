package ec.diners.com.infrastructure.repositories.segment;

import ec.diners.com.domain.entities.segment.Segment;
import ec.diners.com.infrastructure.modelsDb.theme.SegmentModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class SegmentMapperImpl implements SegmentMapper {

    @Override
    public Segment toSegment(SegmentModel model) {
        if ( model == null ) {
            return null;
        }

        Segment segment = new Segment();

        segment.setId( model.getId() );
        segment.setCodeSegment( model.getCodeSegment() );
        segment.setName( model.getName() );

        return segment;
    }

    @Override
    public List<Segment> toSegment(List<SegmentModel> SegmentModel) {
        if ( SegmentModel == null ) {
            return null;
        }

        List<Segment> list = new ArrayList<Segment>( SegmentModel.size() );
        for ( SegmentModel segmentModel : SegmentModel ) {
            list.add( toSegment( segmentModel ) );
        }

        return list;
    }

    @Override
    public SegmentModel toSegmentModel(Segment segment) {
        if ( segment == null ) {
            return null;
        }

        SegmentModel segmentModel = new SegmentModel();

        segmentModel.setId( segment.getId() );
        segmentModel.setCodeSegment( segment.getCodeSegment() );
        segmentModel.setName( segment.getName() );

        return segmentModel;
    }
}
