package ec.diners.com.application.commands.user.get;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.domain.response.Response;

public class GetUserCommand implements Command<Response<UserDto>> {

    private final Long userId;

    public GetUserCommand(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}

