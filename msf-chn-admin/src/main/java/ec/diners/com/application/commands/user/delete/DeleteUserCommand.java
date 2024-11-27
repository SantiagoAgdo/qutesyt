package ec.diners.com.application.commands.user.delete;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public class DeleteUserCommand implements Command<Response<String>> {

    private final Long Id;

    public DeleteUserCommand(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }
}