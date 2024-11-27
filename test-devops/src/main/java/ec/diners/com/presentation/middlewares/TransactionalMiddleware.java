package ec.diners.com.presentation.middlewares;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.domain.constants.LogType;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.services.entityLog.systemLog.CreateEntityLogSystemService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TransactionalMiddleware implements Command.Middleware {

    private final Pipeline mediator;
    private final List<String> commandsToIgnore = Arrays.asList(
            "CreateEntityLogSystemService",
            "CreateEntityLogUserService"
    );

    public TransactionalMiddleware(Pipeline mediator) {
        this.mediator = mediator;
    }

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        if (!commandsToIgnore.contains(command.getClass().getSimpleName()) &&
                !command.getClass().getSimpleName().startsWith("Get") &&
                !command.getClass().getSimpleName().endsWith("Query")) {
            mediator.send(new CreateEntityLogSystemService(
                    LogType.INFO,
                    String.format("Command Send: %s", command.getClass().getSimpleName()))
            );
        }

        R response = next.invoke();
        if (response instanceof Response<?> responseInstance && (!responseInstance.isSuccess())) {
            mediator.send(new CreateEntityLogSystemService(
                    LogType.INFO,
                    String.format("Command: %s response: %s", command.getClass().getSimpleName(), responseInstance.getErrorResponse().getMessage()))
            );
        }
        return response;
    }
}
