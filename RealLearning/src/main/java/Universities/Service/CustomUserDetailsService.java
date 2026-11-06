package Universities.Service;

import Universities.Entity.User;
import Universities.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userToBeAuthenticated = userRepository.findByUsername(username);
        if(userToBeAuthenticated == null)
            throw new UsernameNotFoundException("User not found");

        return org.springframework.security.core.userdetails.User.builder()
                .username(userToBeAuthenticated.getUsername())
                .password(userToBeAuthenticated.getPassword())
                .disabled(!userToBeAuthenticated.getEnabled())
                .roles(String.valueOf(userToBeAuthenticated.getRole()))
                .build();
    }
}
