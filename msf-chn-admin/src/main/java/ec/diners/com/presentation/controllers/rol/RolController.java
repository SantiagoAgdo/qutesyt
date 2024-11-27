package ec.diners.com.presentation.controllers.rol;


import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.roles.create.CreateRolCommand;
import ec.diners.com.application.commands.roles.create.CreateRolFuncionalityCommand;
import ec.diners.com.application.commands.roles.delete.DeleteRolCommand;
import ec.diners.com.application.commands.roles.get.GetRolCommand;
import ec.diners.com.application.commands.roles.update.UpdateRolCommand;
import ec.diners.com.application.dtos.request.roles.CreateRolFuncionalityRequest;
import ec.diners.com.application.dtos.request.roles.CreateRolRequest;
import ec.diners.com.application.dtos.response.roles.CreateRolFuncionalityResponse;
import ec.diners.com.application.dtos.response.roles.CreateRoleResponse;
import ec.diners.com.domain.entities.rol.RoleDto;
import ec.diners.com.domain.response.Response;
import ec.diners.com.presentation.controllers.BaseController;
import ec.diners.com.presentation.controllers.theme.ThemeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rol")
public class RolController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ThemeController.class);

    private final Pipeline mediator;

    public RolController(Pipeline mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createRole(@RequestBody CreateRolRequest createRolRequest) {

        CreateRolCommand createRolCommand = new CreateRolCommand();
        createRolCommand.setName(createRolRequest.getName());
        createRolCommand.setDescription(createRolRequest.getDescription());

        Response<CreateRoleResponse> response = mediator.send(createRolCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Object> getRole(@PathVariable String roleId) {
        GetRolCommand command = new GetRolCommand(roleId);
        Response<RoleDto> response = mediator.send(command);

        if (response.isSuccess()) {
            return createResponse(response.getValue(), HttpStatus.OK);
        } else {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Object> updateRole(@PathVariable String roleId, @RequestBody RoleDto roleDto) {
        roleDto.setRoleId(roleId);
        UpdateRolCommand command = new UpdateRolCommand(roleDto);
        Response<RoleDto> response = mediator.send(command);

        if (response.isSuccess()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Object> deleteRole(@PathVariable String roleId) {
        DeleteRolCommand command = new DeleteRolCommand(roleId);
        Response<String> response = mediator.send(command);

        if (response.isSuccess()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.getValue());
        }
    }

    @PostMapping("/addFunctionality")
    public ResponseEntity<Object> addFunctionalityToRole(@RequestBody CreateRolFuncionalityRequest request) {
        CreateRolFuncionalityCommand command = new CreateRolFuncionalityCommand();
        command.setRoleId(request.getRoleId());
        command.setFuncionalityId(request.getFuncionalityId());

        Response<CreateRolFuncionalityResponse> response = mediator.send(command);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }
}
