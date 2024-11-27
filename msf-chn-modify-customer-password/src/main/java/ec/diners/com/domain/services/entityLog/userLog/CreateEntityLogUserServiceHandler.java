package ec.diners.com.domain.services.entityLog.userLog;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.constants.GlobalConstant;
import ec.diners.com.domain.constants.LogType;
import ec.diners.com.domain.entities.logs.EntityLog;
import ec.diners.com.domain.interfaces.repositories.logs.EntityLogRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateEntityLogUserServiceHandler implements Command.Handler<CreateEntityLogUserService, Response<Boolean>> {

    private static final Logger logger = LoggerFactory.getLogger(CreateEntityLogUserServiceHandler.class);
    private final EntityLogRepository entityLogRepository;

    public CreateEntityLogUserServiceHandler(EntityLogRepository entityLogRepository) {
        this.entityLogRepository = entityLogRepository;
    }

    @Override
    public Response<Boolean> handle(CreateEntityLogUserService service) {
        var processResponse = new ProcessResponse<Boolean>();

        logger.info(String.format("%s, %s", service.action(), service.description()));

        var entityLog = new EntityLog(
                GlobalConstant.MEMBER,
                LogType.INFO,
                service.action(),
                service.description()
        );
        entityLogRepository.add(entityLog);

        return processResponse.success(true);
    }
}
