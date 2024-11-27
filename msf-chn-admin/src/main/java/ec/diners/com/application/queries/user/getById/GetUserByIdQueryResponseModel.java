package ec.diners.com.application.queries.user.getById;

import ec.diners.com.domain.entities.user.User;

import java.util.UUID;

public class GetUserByIdQueryResponseModel {

        private String name;
        private UUID id;
        private String alias;
        private String email;
        private String cellphone;
        private String identification;

        public GetUserByIdQueryResponseModel(User user) {
            this.name = user.getName();
            this.id = user.getId();
            this.alias = user.getAlias();
            this.email = user.getEmail();
            this.cellphone = user.getCellphone();
            this.identification = user.getIdentification();

        }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getEmail() {
        return email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getIdentification() {
        return identification;
    }
}
