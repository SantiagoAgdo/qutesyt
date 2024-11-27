package ec.diners.com.infrastructure.crons;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.domain.constants.LogType;
import ec.diners.com.domain.interfaces.repositories.security.UserAuthTokenRepository;
import ec.diners.com.domain.services.entityLog.systemLog.CreateEntityLogSystemService;
import ec.diners.com.domain.services.security.userAuthToken.getExpiredList.GetUserTokenExpiredListService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class DeleteInvalidTokens {

    private final UserAuthTokenRepository userAuthTokenRepository;
    private final Pipeline mediator;

    public DeleteInvalidTokens(UserAuthTokenRepository userAuthTokenRepository,  Pipeline mediator) {
        this.userAuthTokenRepository = userAuthTokenRepository;
        this.mediator = mediator;
    }


    @Scheduled(cron = "0 0 6 * * *")//every 24 hours at 6AM UTC / 1AM ECU
    public void scheduleInvalidTokens() {
        mediator.send(new CreateEntityLogSystemService(
                LogType.INFO,
                "Delete invalid tokens cron start"
        ));

        var userTokenListResponse = mediator.send(new GetUserTokenExpiredListService());

        var userTokenList = userTokenListResponse.getValue();

        if (!userTokenList.isEmpty()) {
            userAuthTokenRepository.deleteAll(userTokenList);
        }


        mediator.send(new CreateEntityLogSystemService(
                LogType.INFO,
                "Delete invalid tokens cron end"
        ));
    }
}
