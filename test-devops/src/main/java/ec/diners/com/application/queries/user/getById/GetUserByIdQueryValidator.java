package ec.diners.com.application.queries.user.getById;

import ec.diners.com.domain.exceptions.DomainException;
import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.interfaces.CommandValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetUserByIdQueryValidator implements CommandValidator<GetUserByIdQuery, Response<GetUserByIdQueryResponseModel>> {
    public void validate(GetUserByIdQuery query) {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null.");
        }

        UUID userId = query.userId();

        if (userId == null) {
            throw new DomainException("User ID is required and cannot be null.", HttpStatus.BAD_REQUEST);
        }

        try {
            UUID.fromString(userId.toString());
        } catch (IllegalArgumentException e) {
            throw new DomainException("User ID format is invalid.", HttpStatus.BAD_REQUEST);
        }


    }
}