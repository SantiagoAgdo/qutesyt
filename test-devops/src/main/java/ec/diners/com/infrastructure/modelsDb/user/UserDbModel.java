package ec.diners.com.infrastructure.modelsDb.user;

import ec.diners.com.infrastructure.modelsDb.BaseDbModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class UserDbModel extends BaseDbModel {
    @Column(length = 64)
    private String identification;
    @Column(length = 128,nullable = false)
    private String name;
    @Column(length = 128,nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false, unique = true,length = 64)
    private String email;
    private String cellphone;
    @Column(length = 64,unique = true)
    private String alias;
    @Column(length = 512)
    private String password;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;


    public UserDbModel() {
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lasname) {
        this.lastname = lasname;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
}
