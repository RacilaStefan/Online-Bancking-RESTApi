package MainPackage.RestControllers;

import MainPackage.Domain.User;
import MainPackage.Dto.UserDto;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.DatabaseCommunication.UserDbService;
import MainPackage.Services.EntityModel.UserEntityModelService;
import MainPackage.Services.Utils.Implementations.Utilities;
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
import java.time.LocalDateTime;

@Api(tags = "Authentication Controller")
@RequestMapping("/auth")
@RestController

@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authManager;
    private final UserEntityModelService userService;
    private final UserDbService userDbService;

    private final Utilities util;

    //#######  GET ENDPOINTS  #######//

    @GetMapping()
    private EntityModel<UserDto> getAuth() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userService.findById(user.getId());
    }

    @GetMapping("/logout")
    private ResponseEntity<String> logout() {
        SecurityContextHolder.getContext().setAuthentication(null);

        return new ResponseEntity<>("The user was logged out.", HttpStatus.OK);
    }

    //#######  GET ENDPOINTS  #######//

    //#######  POST ENDPOINTS  #######//

    @PostMapping("/login")
    private ResponseEntity<String> authenticate(@Valid @RequestBody AuthRequest request) throws CustomInvalidInputException {

        User user = userDbService.findByUsername(request.getUsername());
        if (user == null)
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

        if (!user.isEnabled()) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        if (request.getTwoFACode() == null || !request.getTwoFACode().matches("[0-9]{6}")) {
            util.send2FACode(user);
            return new ResponseEntity<>("Code sent on email", HttpStatus.OK);
        }

        if (LocalDateTime.now().isAfter(user.getToken().getTwoFACodeExpiration())) {
            util.send2FACode(user);
            return new ResponseEntity<>("Code refreshed", HttpStatus.OK);
        }

        if (!request.getTwoFACode().equals(user.getToken().getTwoFACode())) {
            return new ResponseEntity<>("Bad code", HttpStatus.UNAUTHORIZED);
        }

        try {
            Authentication authentication = authManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            user.getToken().setTwoFACode(null);
            user.getToken().setTwoFACodeExpiration(null);

            return new ResponseEntity<>("The user was logged in.", HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>("Bad Credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/enable")
    private ResponseEntity<String> enableUser(@RequestBody String token) throws CustomInvalidInputException {
        util.enableUser(token);
        return new ResponseEntity<>("Verification completed", HttpStatus.OK);
    }

    //#######  POST ENDPOINTS  #######//
}
