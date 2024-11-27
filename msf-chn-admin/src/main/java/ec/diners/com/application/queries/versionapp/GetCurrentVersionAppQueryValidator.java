package ec.diners.com.application.queries.versionapp;

import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.interfaces.CommandValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GetCurrentVersionAppQueryValidator implements CommandValidator<GetCurrentVersionAppQuery, Response<GetCurrentVersionAppQueryResponseModel>> {
    @Override
    public void validate(GetCurrentVersionAppQuery query) {
        var validationErrors = new ArrayList<String>();

    }
}
