package Universities.Mapper;

import Universities.DTO.UserCreatedResponse;
import Universities.DTO.UserCreationDTO;
import Universities.Entity.User;

public final class UserMapper {

    public static User createUserMapped(UserCreationDTO userCreationDTO){
        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        user.setEmail(userCreationDTO.getEmail());
        user.setPassword(userCreationDTO.getPassword());

        return user;
    }

    public static UserCreatedResponse userCreatedResponse (User user){
        UserCreatedResponse userCreatedResponse = new UserCreatedResponse();
        userCreatedResponse.setId(user.getId());
        userCreatedResponse.setUsername(user.getUsername());
        userCreatedResponse.setEmail(user.getEmail());
        userCreatedResponse.setEnabled(user.getEnabled());
        userCreatedResponse.setRole(user.getRole());

        return userCreatedResponse;
    }
}
