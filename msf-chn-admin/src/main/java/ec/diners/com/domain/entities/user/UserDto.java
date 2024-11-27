package ec.diners.com.domain.entities.user;

import lombok.Data;

@Data
public class UserDto {

    private String id;
    private String name;
    private String lastName;
    private String identificationNumber;
    private String identificationType;
    private String telephoneNumber;
    private String photo;
    private String email;
    private String pwd;
    private String token;
    private boolean enabled;

}
