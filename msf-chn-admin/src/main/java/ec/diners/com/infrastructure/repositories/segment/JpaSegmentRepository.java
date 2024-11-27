package ec.diners.com.infrastructure.repositories.segment;

import ec.diners.com.infrastructure.modelsDb.theme.SegmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaSegmentRepository extends JpaRepository<SegmentModel, Long> {

    Optional<SegmentModel> findByCodeSegment(Integer codeSegment);
}
