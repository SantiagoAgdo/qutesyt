package ec.diners.com.infrastructure.repositories.user.mapper;

import ec.diners.com.application.dtos.request.user.CreateUserRequest;
import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.infrastructure.modelsDb.user.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUser(UserModel model) {
        if ( model == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( model.getId() != null ) {
            userDto.setId( String.valueOf( model.getId() ) );
        }
        userDto.setEmail( model.getEmail() );
        userDto.setEnabled( model.isEnabled() );
        userDto.setIdentificationNumber( model.getIdentificationNumber() );
        userDto.setIdentificationType( model.getIdentificationType() );
        userDto.setLastName( model.getLastName() );
        userDto.setName( model.getName() );
        userDto.setPhoto( model.getPhoto() );
        userDto.setPwd( model.getPwd() );
        userDto.setTelephoneNumber( model.getTelephoneNumber() );
        userDto.setToken( model.getToken() );

        return userDto;
    }

    @Override
    public List<UserDto> toUser(List<UserModel> userModels) {
        if ( userModels == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userModels.size() );
        for ( UserModel userModel : userModels ) {
            list.add( toUser( userModel ) );
        }

        return list;
    }

    @Override
    public UserModel toUserModel(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        if ( userDto.getId() != null ) {
            userModel.id( Long.parseLong( userDto.getId() ) );
        }
        userModel.email( userDto.getEmail() );
        userModel.enabled( userDto.isEnabled() );
        userModel.identificationNumber( userDto.getIdentificationNumber() );
        userModel.identificationType( userDto.getIdentificationType() );
        userModel.lastName( userDto.getLastName() );
        userModel.name( userDto.getName() );
        userModel.photo( userDto.getPhoto() );
        userModel.pwd( userDto.getPwd() );
        userModel.telephoneNumber( userDto.getTelephoneNumber() );
        userModel.token( userDto.getToken() );

        return userModel.build();
    }

    @Override
    public UserModel toUserModel(CreateUserRequest createUserRequest) {
        if ( createUserRequest == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.email( createUserRequest.getEmail() );
        userModel.enabled( createUserRequest.isEnabled() );
        userModel.identificationNumber( createUserRequest.getIdentificationNumber() );
        userModel.identificationType( createUserRequest.getIdentificationType() );
        userModel.lastName( createUserRequest.getLastName() );
        userModel.name( createUserRequest.getName() );
        userModel.photo( createUserRequest.getPhoto() );
        userModel.pwd( createUserRequest.getPwd() );
        userModel.telephoneNumber( createUserRequest.getTelephoneNumber() );
        userModel.token( createUserRequest.getToken() );

        return userModel.build();
    }
}
