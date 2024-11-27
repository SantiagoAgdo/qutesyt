package ec.diners.com.application.commands.user.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordCommand implements Command<Response<Void>> {
    private String email;
    private String oldPassword;
    private String newPassword;
}

