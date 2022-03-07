package MainPackage.Dto;

import MainPackage.Domain.*;
import MainPackage.EnumsAndStaticClasses.UserRole;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private String email;
    private String telephoneNumber;
    private UserRole role;
    private Boolean locked;
    private Boolean enabled;

    @JsonIgnore
    private EntityModelGenerator generator = new EntityModelGenerator();

    public User fromDto(){
        User user = new User();
        user.setId(this.id);
        user.setLastName(this.lastName);
        user.setFirstName(this.firstName);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setTelephoneNumber(this.telephoneNumber);
        user.setRole(this.role);
        user.setLocked(this.locked);
        user.setEnabled(this.enabled);

        return user;
    }

    public UserDto getDto(User user) {
        this.setId(user.getId());
        this.setLastName(user.getLastName());
        this.setFirstName(user.getFirstName());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setTelephoneNumber(user.getTelephoneNumber());
        this.setRole(user.getRole());
        this.setLocked(user.getLocked());
        this.setEnabled(user.getEnabled());

        return this;
    }
}
