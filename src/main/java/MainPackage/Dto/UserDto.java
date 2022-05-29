package MainPackage.Dto;

import MainPackage.Domain.*;
import MainPackage.EnumsAndStaticClasses.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z \\-']{2,100}")
    private String lastName;

    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z \\-']{2,100}")
    private String firstName;

    @NotNull
    @Pattern(regexp = "[A-Za-z0-9_]{8,100}")
    private String username;

    @NotNull
    @Pattern(regexp = "[A-Za-z0-9~!@#$%^&*()_=+\\[{};:'\"<>,.?\\]\\-]{12,100}")
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp="[0-9]{10}")
    private String telephoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_USER;

    @NotNull
    private Boolean locked = false;
    @NotNull
    private Boolean enabled = true;

    @NotNull
    private AddressDto address;
    @NotNull
    private Set<AccountDto> accounts;
    @NotNull
    private CIDto ci;

    public User fromDto(){
        User user = new User();

        user.setLastName(this.lastName);
        user.setFirstName(this.firstName);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setTelephoneNumber(this.telephoneNumber);
        user.setRole(this.role);
        user.setLocked(this.locked);
        user.setEnabled(this.enabled);

        user.setAddress(this.address.fromDto());
        user.setAccounts(this.accounts.stream().map(AccountDto::fromDto).collect(Collectors.toSet()));
        user.setCi(this.ci.fromDto());

        return user;
    }

    public UserDto getDto(User user, boolean withPassword) {
        this.setId(user.getId());
        this.setLastName(user.getLastName());
        this.setFirstName(user.getFirstName());
        this.setUsername(user.getUsername());
        if (withPassword)
            this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setTelephoneNumber(user.getTelephoneNumber());
        this.setRole(user.getRole());
        this.setLocked(user.getLocked());
        this.setEnabled(user.getEnabled());

        return this;
    }
}
