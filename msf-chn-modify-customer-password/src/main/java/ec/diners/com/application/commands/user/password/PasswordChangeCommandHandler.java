package ec.diners.com.application.commands.user.password;

import an.awesome.pipelinr.Command;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.diners.com.domain.interfaces.webApiClient.WebApiClient;
import ec.diners.com.domain.response.ErrorResponse;
import ec.diners.com.domain.response.Response;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class PasswordChangeCommandHandler implements Command.Handler<PasswordChangeCommand, Response<PasswordChangeCommandResponse>> {

    private final WebApiClient webApiClient;
    private final ObjectMapper objectMapper;

    @Value("${api-director.root}")
    private String apiDirectorRoot;

    @Value("${api-director.modifyCustomerPassword-path}")
    private String modifyCustomerPasswordPath;

    @Override
    public Response<PasswordChangeCommandResponse> handle(PasswordChangeCommand command) {
        try {

            String requestBody = serializeRequest(command);

            List<String> headers = prepareHeaders(command.dinHeader());

            String apiUrl = buildApiUrl(modifyCustomerPasswordPath);

            String apiResponse = webApiClient.post(
                    apiUrl,
                    requestBody,
                    headers
            );

            return processApiResponse(apiResponse);

        } catch (WebClientResponseException | JsonProcessingException e) {
            String errorDetail;
            if (e instanceof WebClientResponseException webClientEx) {
                errorDetail =  webClientEx.getResponseBodyAsString();
            } else {
                errorDetail = e.getMessage();
            }
            return new Response<>(new ErrorResponse("Error in the password change process", errorDetail));
        }
    }

    private String serializeRequest(PasswordChangeCommand command) throws JsonProcessingException {
        return objectMapper.writeValueAsString(command);
    }

    private List<String> prepareHeaders(PasswordChangeCommand.PasswordChangeHeaderCommand header) {
        return List.of(
                "X-Application-Id:" + header.aplicacionId(),
                "X-Channel-Id:" + header.canalId(),
                "X-Session-Id:" + header.sesionId(),
                "X-Transaction-Time:" + header.horaTransaccion()
        );
    }
    private String buildApiUrl(String path) {
        return apiDirectorRoot + path;
    }

    private Response<PasswordChangeCommandResponse> processApiResponse(String apiResponse) {
        if (apiResponse == null) {
            return new Response<>(new ErrorResponse("Error", "No response from the server"));
        }

        try {

            PasswordChangeCommandResponse response = objectMapper.readValue(
                    apiResponse,
                    PasswordChangeCommandResponse.class
            );


            if (isErrorResponse(response.dinError())) {
                return new Response<>(new ErrorResponse(
                        response.dinError().codigo(),
                        response.dinError().mensaje()
                ));
            }

            return new Response<>(response);

        } catch (JsonProcessingException e) {
            return new Response<>(new ErrorResponse("Error", "Error processing server response"));
        }
    }

    private boolean isErrorResponse(PasswordChangeCommandResponse.PasswordChangeErrorCommandResponse error) {
        return error != null &&
                !"0000".equals(error.codigo()) &&
                !"N".equals(error.tipo());
    }
}
