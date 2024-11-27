package ec.diners.com.domain.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VersionViewImpl implements  VersionView{
    private Boolean requiredAndroid;
    private Boolean requiredIos;
    private Boolean clearStorage;

    @Override
    public Boolean getRequiredAndroid() {
        return requiredAndroid;
    }

    @Override
    public Boolean getRequiredIos() {
        return requiredIos;
    }

    @Override
    public Boolean getClearStorage() {
        return clearStorage;
    }
}
