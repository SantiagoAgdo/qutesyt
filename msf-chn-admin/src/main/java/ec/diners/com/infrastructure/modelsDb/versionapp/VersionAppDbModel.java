package ec.diners.com.infrastructure.modelsDb.versionapp;

import ec.diners.com.infrastructure.modelsDb.BaseDbModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "versions_app")
public class VersionAppDbModel extends BaseDbModel {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "version_number",nullable = false)
    private String versionNumber;

    @Column(name = "update_required_android")
    private Boolean updateRequiredAndroid;

    @Column(name = "update_required_ios")
    private Boolean updateRequiredIos;

    @Column(name = "clear_storage")
    private Boolean clearStorage;

    @Column(name = "published_date", nullable = false)
    private Date publishedDate;

    @Column(name = "is_offline")
    private Boolean isOffline;

    public VersionAppDbModel() {
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
