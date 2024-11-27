package ec.diners.com.domain.entities.versionapp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ec.diners.com.domain.entities.BaseEntity;

import java.util.Date;

public class VersionApp extends BaseEntity {
    private String versionNumber;
    private Boolean updateRequiredAndroid;
    private Boolean updateRequiredIos;
    private Boolean clearStorage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date publishedDate;

    @JsonIgnore
    private Boolean isOffline;

    public VersionApp() {
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Boolean getUpdateRequiredAndroid() {
        return updateRequiredAndroid;
    }

    public void setUpdateRequiredAndroid(Boolean updateRequiredAndroid) {
        this.updateRequiredAndroid = updateRequiredAndroid;
    }

    public Boolean getUpdateRequiredIos() {
        return updateRequiredIos;
    }

    public void setUpdateRequiredIos(Boolean updateRequiredIos) {
        this.updateRequiredIos = updateRequiredIos;
    }

    public Boolean getClearStorage() {
        return clearStorage;
    }

    public void setClearStorage(Boolean clearStorage) {
        this.clearStorage = clearStorage;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Boolean getOffline() {
        return isOffline;
    }

    public void setOffline(Boolean offline) {
        isOffline = offline;
    }
}
