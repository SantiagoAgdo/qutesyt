package ec.diners.com.infrastructure.repositories.partnerIntegration;

import ec.diners.com.application.dtos.response.partnerIntegration.PartnerIntegrationDto;
import ec.diners.com.infrastructure.modelsDb.partnerIntegration.PartnerIntegrationModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartnerIntegrationMapper {
    @Mapping(source="id", target="id")
    @Mapping(source="dinersId", target="dinersId")
    @Mapping(source="name", target="name")
    @Mapping(source="themeId", target="themeId")
    @Mapping(source="cardProducts", target="cardProducts")
    @Mapping(source="codeSegment", target="codeSegment")
    @Mapping(source="isRegistered", target="isRegistered")

    @Mapping(source="latitude", target="latitude")
    @Mapping(source="longitude", target="longitude")
    PartnerIntegrationDto promtoPartnerIntegrationDto(PartnerIntegrationModel model);

    List<PartnerIntegrationDto> toEntities(List<PartnerIntegrationModel> models);

    @InheritInverseConfiguration
    PartnerIntegrationModel toPartnerIntegrationModel(PartnerIntegrationDto partnerIntegrationDto);

}
