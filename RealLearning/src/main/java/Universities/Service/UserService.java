package Universities.Service;

import Universities.DTO.UserCreatedResponse;
import Universities.DTO.UserCreationDTO;
import Universities.Entity.Role;
import Universities.Entity.User;
import Universities.Mapper.UserMapper;
import Universities.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserCreatedResponse createNewUser(UserCreationDTO userCreationDTO){
        User user = UserMapper.createUserMapped(userCreationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(Role.USER);
        userRepository.save(user);
        return UserMapper.userCreatedResponse(user);
    }
}
