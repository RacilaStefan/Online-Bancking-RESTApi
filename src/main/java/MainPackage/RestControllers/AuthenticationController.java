package MainPackage.RestControllers;

import MainPackage.Domain.User;
import MainPackage.Dto.UserDto;
import MainPackage.Services.EntityModel.UserEntityModelService;
import MainPackage.Services.Utils.Models.AuthRequest;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Authentication Controller")
@RequestMapping("/auth")
@RestController

@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authManager;
    private final UserEntityModelService userService;

    //#######  GET ENDPOINTS  #######//

    @GetMapping()
    private EntityModel<UserDto> getAuth() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userService.findById(user.getId());
    }

    @GetMapping("/logout")
    private ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();

        return new ResponseEntity<>("The user was logged out.", HttpStatus.OK);
    }

    //#######  GET ENDPOINTS  #######//

    //#######  POST ENDPOINTS  #######//

    @PostMapping("/login")
    private ResponseEntity<String> authenticate(@Valid @RequestBody AuthRequest request) {

        try {
            Authentication authentication = authManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>("The user was logged in.", HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>("Bad Credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    //#######  POST ENDPOINTS  #######//
}
