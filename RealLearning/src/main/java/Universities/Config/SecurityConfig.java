package Universities.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity    .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/user").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/students/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails admin = User.builder()
                            .username("gabriel")
                            .password(passwordEncoder.encode("temporary_password"))
                            .roles("ADMIN")
                            .build();
        UserDetails regularUser = User.builder()
                            .username("john")
                            .password(passwordEncoder.encode("temp-pwd"))
                            .roles("USER")
                            .build();
        return new InMemoryUserDetailsManager(admin, regularUser);
    }
    **/



}

