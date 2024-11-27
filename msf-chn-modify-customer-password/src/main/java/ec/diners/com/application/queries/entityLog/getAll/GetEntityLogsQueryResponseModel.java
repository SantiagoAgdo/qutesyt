package ec.diners.com.application.queries.entityLog.getAll;

import ec.diners.com.domain.entities.logs.EntityLog;
import ec.diners.com.domain.interfaces.models.QueryResponseModel;

import java.util.Date;
import java.util.UUID;

public class GetEntityLogsQueryResponseModel extends QueryResponseModel<GetEntityLogsQueryResponseModel, EntityLog> {
    private UUID id;
    protected Date createAt;
    private String createdBy;
    private String logType;
    private String actionType;
    private String description;

    @Override
    public GetEntityLogsQueryResponseModel fromEntity(EntityLog entity) {
        var responseModel = new GetEntityLogsQueryResponseModel();
        responseModel.id = entity.getId();
        responseModel.createAt = entity.getCreateAt();
        responseModel.createdBy = entity.getCreatedBy();
        responseModel.logType = entity.getLogType();
        responseModel.actionType = entity.getActionType();
        responseModel.description = entity.getDescription();

        return responseModel;
    }

    public UUID getId() {
        return id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLogType() {
        return logType;
    }

    public String getActionType() {
        return actionType;
    }

    public String getDescription() {
        return description;
    }
}
