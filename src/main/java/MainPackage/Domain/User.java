package MainPackage.Domain;

import MainPackage.EnumsAndStaticClasses.UserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

@Entity
@Table (name = "User",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"username", "email", "telephoneNumber"}) })

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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
    private Boolean enabled = false;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "address_id", referencedColumnName = "id")
    private Address address;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "ci_id", referencedColumnName = "id")
    private CI ci;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "token_id", referencedColumnName = "id")
    private Token token;


    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        if (!Objects.equals(firstName, user.firstName)) return false;
        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(telephoneNumber, user.telephoneNumber)) return false;
        if (role != user.role) return false;
        if (!Objects.equals(locked, user.locked)) return false;
        if (!Objects.equals(enabled, user.enabled)) return false;
        if (!Objects.equals(token, user.token)) return false;
        if (!Objects.equals(address, user.address)) return false;
        if (!Objects.equals(ci, user.ci)) return false;
        return Objects.equals(accounts, user.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, username, password, email, telephoneNumber, role, locked, enabled, token, address, ci, accounts);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", role=" + role +
                ", locked=" + locked +
                ", enabledVar=" + enabled +
                ", tokenTable=" + token +
                ", address=" + address +
                ", ci=" + ci +
                ", accounts=" + accounts +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
