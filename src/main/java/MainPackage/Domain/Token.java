package MainPackage.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table (name = "Token",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"registrationToken", "passwordChangeToken", "paymentToken"}) })

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationToken;

    private LocalDateTime registrationTokenExpiration;

    private String passwordChangeToken;

    private LocalDateTime passwordChangeTokenExpiration;

    private String paymentToken;

    private LocalDateTime paymentTokenExpiration;

    private String twoFACode;

    private LocalDateTime twoFACodeExpiration;

    @JsonBackReference
    @OneToOne (mappedBy = "token")
    private User user;

    public Token() {
    }

    public void setRegistrationToken() {
        registrationToken = UUID.randomUUID().toString();
        registrationTokenExpiration = LocalDateTime.now().plusHours(1);
    }

}
