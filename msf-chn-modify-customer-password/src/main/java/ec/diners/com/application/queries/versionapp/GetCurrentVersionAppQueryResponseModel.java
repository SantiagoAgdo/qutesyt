package ec.diners.com.application.queries.versionapp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ec.diners.com.application.utils.DateSerializer;
import ec.diners.com.domain.entities.versionapp.VersionApp;

import java.util.Date;
import java.util.UUID;

public class GetCurrentVersionAppQueryResponseModel {
    private UUID id;
    private String versionNumber;

    @JsonSerialize(using = DateSerializer.class)
    private Date publishedDate;

    private Boolean updatedRequiredAndroid;
    private Boolean updatedRequiredIos;
    private Boolean clearStorage;

    public GetCurrentVersionAppQueryResponseModel() {
    }

    public GetCurrentVersionAppQueryResponseModel fromEntity(VersionApp entity) {
        var responseModel = new GetCurrentVersionAppQueryResponseModel();
        responseModel.id = entity.getId();
        responseModel.versionNumber = entity.getVersionNumber();
        responseModel.publishedDate = entity.getPublishedDate();
        responseModel.updatedRequiredAndroid = entity.getUpdateRequiredAndroid();
        responseModel.updatedRequiredIos = entity.getUpdateRequiredIos();
        responseModel.clearStorage = entity.getClearStorage();

        return responseModel;
    }

    public UUID getId() {
        return id;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public Boolean getUpdatedRequiredAndroid() {
        return updatedRequiredAndroid;
    }

    public Boolean getUpdatedRequiredIos() {
        return updatedRequiredIos;
    }

    public Boolean getClearStorage() {
        return clearStorage;
    }
}
