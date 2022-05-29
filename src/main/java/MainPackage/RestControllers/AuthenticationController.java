package MainPackage.RestControllers;

import MainPackage.Domain.User;
import MainPackage.Dto.UserDto;
import MainPackage.EnumsAndStaticClasses.UserRole;
import MainPackage.Services.DatabaseCommunication.UserDbService;
import MainPackage.Services.Utils.Models.AuthRequest;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@AllArgsConstructor

@RequestMapping("/auth")

@RestController
public class AuthenticationController {

    private final AuthenticationManager authManager;
    private final UserDbService userService;

    @PostMapping("/login")
    private ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {

        try {
            Authentication authentication = authManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/logout")
    private ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    private EntityModel<UserDto> getAuth() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userService.findById(user.getId());
    }
}
