package ec.diners.com.application.queries.user.getById;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.user.base.UserRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.springframework.stereotype.Component;

@Component
public class GetUserByIdQueryHandler implements Command.Handler<GetUserByIdQuery, Response<GetUserByIdQueryResponseModel>> {
    private final UserRepository userRepository;

    public GetUserByIdQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Response<GetUserByIdQueryResponseModel> handle(GetUserByIdQuery query) {
        var processResponse = new ProcessResponse<GetUserByIdQueryResponseModel>();

        var user = userRepository.getById(query.userId());

        if (user == null) {
            return processResponse.error("User not found");
        }

        if (!user.getIsActive()) {
            return processResponse.error("User is inactive");
        }

        var responseModel = new GetUserByIdQueryResponseModel(user);

        return processResponse.success(responseModel);
    }
}