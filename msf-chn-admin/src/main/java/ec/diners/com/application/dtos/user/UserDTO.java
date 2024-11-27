package ec.diners.com.application.dtos.user;

import ec.diners.com.domain.entities.user.User;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String name;
    private String lastName;
    private String alias;
    private String email;
    private String cellphone;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastname();
        this.alias = user.getAlias();
        this.email = user.getEmail();
        this.cellphone = user.getCellphone();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
