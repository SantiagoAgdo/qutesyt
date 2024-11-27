package ec.diners.com.domain.interfaces.services.personality;

import ec.diners.com.domain.entities.personality.PersonalizationValidationDto;

import java.util.concurrent.CompletableFuture;

public interface IAsyncThemePersonalityService {

    CompletableFuture<Boolean> validationThemesByPriority(PersonalizationValidationDto personalizationValidationDto);

}