package ec.diners.com.domain.services.entityLog.systemLog;

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
public class CreateEntityLogSystemServiceHandler implements Command.Handler<CreateEntityLogSystemService, Response<Boolean>> {

    private static final Logger logger = LoggerFactory.getLogger(CreateEntityLogSystemServiceHandler.class);
    private final EntityLogRepository entityLogRepository;

    public CreateEntityLogSystemServiceHandler(EntityLogRepository entityLogRepository) {
        this.entityLogRepository = entityLogRepository;
    }

    @Override
    public Response<Boolean> handle(CreateEntityLogSystemService service) {
        var processResponse = new ProcessResponse<Boolean>();

        if (service.logType().equals(LogType.ERROR)) {
            logger.error(service.description());
        } else if (service.logType().equals(LogType.WARNING)) {
            logger.warn(service.description());
        }
        else {
            logger.info(service.description());
        }

        var entityLog = new EntityLog(
                GlobalConstant.SYSTEM,
                service.logType(),
                service.description()
        );
        entityLogRepository.add(entityLog);

        return processResponse.success(true);
    }
}
