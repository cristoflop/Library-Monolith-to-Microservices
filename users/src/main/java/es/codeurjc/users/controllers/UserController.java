package es.codeurjc.users.controllers;

import es.codeurjc.users.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.users.dtos.requests.UserRequestDto;
import es.codeurjc.users.dtos.responses.UserResponseDto;
import es.codeurjc.users.services.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public Collection<UserResponseDto> getUsers() {
        return this.userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@Parameter(description = "id of user to be searched")
                                   @PathVariable long userId) {
        return this.userService.findById(userId);
    }

    @GetMapping("/nick/{nick}")
    public UserResponseDto getUserByName(@Parameter(description = "name of user to be searched")
                                         @PathVariable String nick) {
        return this.userService.findByNick(nick);
    }

    @PostMapping("/")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return this.userService.save(userRequestDto);
    }

    @PatchMapping("/{userId}")
    public UserResponseDto updateUserEmail(@Parameter(description = "id of user to update the email")
                                           @PathVariable long userId,
                                           @Valid @RequestBody UpdateUserEmailRequestDto updateUserEmailRequestDto) {
        return this.userService.updateEmail(userId, updateUserEmailRequestDto);
    }

    @DeleteMapping("/{userId}")
    public UserResponseDto deleteUser(@Parameter(description = "id of user to be deleted") @PathVariable long userId) {
        return this.userService.delete(userId);
    }

}
