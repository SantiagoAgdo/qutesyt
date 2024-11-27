package ec.diners.com.application.dtos.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponse {

    private Long id;
    private String name;
    private String lastName;
    private String identificationNumber;
    private String identificationType;
    private String telephoneNumber;
    private String photo;
    private String email;
    private String pwd;
    private String token;
    private String enabled;

    public GetUserResponse(Long id, String name, String lastName, String identificationNumber, String identificationType, String telephoneNumber, String photo, String email, String pwd, String token, String enabled) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;
        this.telephoneNumber = telephoneNumber;
        this.photo = photo;
        this.email = email;
        this.pwd = pwd;
        this.token = token;
        this.enabled = enabled;
    }
}
