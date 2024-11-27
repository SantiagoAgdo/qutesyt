package ec.diners.com.presentation.controllers.user;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.user.create.CreateUserCommand;
import ec.diners.com.application.commands.user.delete.DeleteUserCommand;
import ec.diners.com.application.commands.user.get.GetUserCommand;
import ec.diners.com.application.commands.user.get.RecoverPasswordCommand;
import ec.diners.com.application.commands.user.login.LoginUserCommand;
import ec.diners.com.application.commands.user.update.UpdateEnabledCommand;
import ec.diners.com.application.commands.user.update.UpdatePasswordCommand;
import ec.diners.com.application.dtos.request.user.*;
import ec.diners.com.application.dtos.response.user.CreateUserResponse;
import ec.diners.com.application.dtos.response.user.RecoverUserResponse;
import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.domain.response.Response;
import ec.diners.com.presentation.controllers.BaseController;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UsersController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final Pipeline mediator;

    public UsersController(Pipeline mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setName(createUserRequest.getName());
        createUserCommand.setLastName(createUserRequest.getLastName());
        createUserCommand.setIdentificationNumber(createUserRequest.getIdentificationNumber());
        createUserCommand.setIdentificationType(createUserRequest.getIdentificationType());
        createUserCommand.setTelephoneNumber(createUserRequest.getTelephoneNumber());
        createUserCommand.setPhoto(createUserRequest.getPhoto());
        createUserCommand.setEmail(createUserRequest.getEmail());
        createUserCommand.setPwd(createUserRequest.getPwd());
        createUserCommand.setToken(createUserRequest.getToken());
        createUserCommand.setEnabled(createUserRequest.isEnabled());

        Response<CreateUserResponse> response = mediator.send(createUserCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        GetUserCommand command = new GetUserCommand(userId);
        Response<UserDto> response = mediator.send(command);

        if (response.isSuccess()) {
            return createResponse(response.getValue(), HttpStatus.OK);
        } else {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<Object> updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        UpdatePasswordCommand updatePasswordCommand = new UpdatePasswordCommand();
        updatePasswordCommand.setEmail(updatePasswordRequest.getEmail());
        updatePasswordCommand.setOldPassword(updatePasswordRequest.getOldPassword());
        updatePasswordCommand.setNewPassword(updatePasswordRequest.getNewPassword());

        Response<Void> response = mediator.send(updatePasswordCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-enabled")
    public ResponseEntity<Object> updateEnabled(@Valid @RequestBody UpdateEnabledRequest updateEnabledRequest) {
        UpdateEnabledCommand updateEnabledCommand = new UpdateEnabledCommand();
        updateEnabledCommand.setEmail(updateEnabledRequest.getEmail());
        updateEnabledCommand.setEnabled(updateEnabledRequest.isEnabled());

        Response<Void> response = mediator.send(updateEnabledCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        LoginUserCommand loginUserCommand = new LoginUserCommand();
        loginUserCommand.setEmail(loginUserRequest.getEmail());
        loginUserCommand.setPwd(loginUserRequest.getPwd());

        Response<UserDto> response = mediator.send(loginUserCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.UNAUTHORIZED);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PostMapping("/recover-password")
    public ResponseEntity<Object> recoverPassword(@Valid @RequestBody RecoverPasswordRequest recoverPasswordRequest) {
        RecoverPasswordCommand recoverPasswordCommand = new RecoverPasswordCommand();
        recoverPasswordCommand.setEmail(recoverPasswordRequest.getEmail());

        Response<RecoverUserResponse> response = mediator.send(recoverPasswordCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Contraseña temporal enviada al correo", HttpStatus.OK);
    }

}

