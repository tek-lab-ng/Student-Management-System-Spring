package Universities.Controllers;

import Universities.DTO.UserCreatedResponse;
import Universities.DTO.UserCreationDTO;
import Universities.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService1){
        this.userService = userService1;
    }

    @PostMapping
    public ResponseEntity<UserCreatedResponse> createNewUser(@RequestBody @Validated UserCreationDTO userCreationDTO){
        UserCreatedResponse userCreatedResponse = userService.createNewUser(userCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreatedResponse);
    }
}
