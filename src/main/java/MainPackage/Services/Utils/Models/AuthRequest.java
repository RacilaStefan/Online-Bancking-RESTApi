package MainPackage.Services.Utils.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class AuthRequest {

    @NotNull
    @Pattern(regexp = "[A-Za-z0-9_]{8,100}")
    private String username;

    @NotNull
    @Pattern(regexp = "[A-Za-z0-9~!@#$%^&*()_=+\\[{};:'\"<>,.?\\]\\-]{12,100}")
    private String password;
}
