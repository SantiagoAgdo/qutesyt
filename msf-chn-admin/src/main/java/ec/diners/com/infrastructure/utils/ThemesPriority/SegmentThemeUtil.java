package ec.diners.com.infrastructure.utils.ThemesPriority;

import ec.diners.com.domain.entities.segment.Segment;
import ec.diners.com.domain.entities.themeSegment.SegmentTheme;
import ec.diners.com.domain.interfaces.repositories.segment.ISegmentRepository;
import ec.diners.com.domain.interfaces.repositories.themeSegment.ISegmentThemeRepository;
import ec.diners.com.domain.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SegmentThemeUtil {

    private final ISegmentRepository segmentRepository;
    private final ISegmentThemeRepository segmentThemeRepository;
    private final Long defaultThemeId;

    /**
     * Constructor con dependencias inyectadas.
     *
     * @param segmentRepository        Repositorio para obtener segmentos.
     * @param segmentThemeRepository   Repositorio para obtener temas de segmentos.
     */
    public SegmentThemeUtil(ISegmentRepository segmentRepository, ISegmentThemeRepository segmentThemeRepository) {
        this.segmentRepository = segmentRepository;
        this.segmentThemeRepository = segmentThemeRepository;
        this.defaultThemeId = Constants.PARAM_DEFAUL_THEME_APP;
    }

    /**
     * Obtiene el ID de tema asociado a un segmento o campaña basado en el código de segmento.
     *
     * @param codeSegment Código del segmento.
     * @param column      Columna que define si se busca el tema de segmento o campaña.
     * @return ID del tema o el tema predeterminado si no se encuentra.
     */
    public Long getSegmentThemeOrCampaignTheme(Integer codeSegment, String column) {
        // Validación inicial
        if (codeSegment == null || column == null) {
            return defaultThemeId;
        }

        // Buscar segmento por código
        Optional<Segment> segmentOpt = Optional.ofNullable(segmentRepository.findByCodeSegment(codeSegment));

        // Obtener SegmentTheme activo si el segmento existe
        Optional<SegmentTheme> segmentThemeOpt = segmentOpt
                .map(Segment::getId)
                .map(segmentThemeRepository::findBySegmentIdAndIsActiveTrue);

        // Retornar el ID del tema según la columna solicitada
        return segmentThemeOpt.map(segmentTheme -> {
            switch (column) {
                case Constants.CAMPAIGN:
                    return Optional.ofNullable(segmentTheme.getThemeCampaignId())
                            .orElse(defaultThemeId);
                case Constants.SEGMENT:
                    return Optional.ofNullable(segmentTheme.getThemeSegmentId())
                            .orElse(defaultThemeId);
                default:
                    return defaultThemeId;
            }
        }).orElse(defaultThemeId);
    }
}
