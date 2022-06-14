package MainPackage.Dto;

import MainPackage.Domain.Token;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TokenDto {

    private Long id;

    @NotNull
    private String registrationToken;

    @NotNull
    private LocalDateTime registrationTokenExpiration;

    private String passwordChangeToken;

    private LocalDateTime passwordChangeTokenExpiration;

    private String paymentToken;

    private LocalDateTime paymentTokenExpiration;

    private String twoFACode;

    private LocalDateTime twoFACodeExpiration;

    public Token fromDto() {
        Token token = new Token();

        token.setId(this.id);
        token.setRegistrationToken(this.registrationToken);
        token.setRegistrationTokenExpiration(this.registrationTokenExpiration);
        token.setPasswordChangeToken(this.passwordChangeToken);
        token.setPasswordChangeTokenExpiration(this.passwordChangeTokenExpiration);
        token.setPaymentToken(this.paymentToken);
        token.setPaymentTokenExpiration(this.paymentTokenExpiration);
        token.setTwoFACode(this.twoFACode);
        token.setTwoFACodeExpiration(this.twoFACodeExpiration);

        return token;
    }


    public TokenDto getDto(Token token) {
        this.setId(token.getId());
        this.setRegistrationToken(token.getRegistrationToken());
        this.setRegistrationTokenExpiration(token.getRegistrationTokenExpiration());
        this.setPasswordChangeToken(token.getPasswordChangeToken());
        this.setPasswordChangeTokenExpiration(token.getPasswordChangeTokenExpiration());
        this.setPaymentToken(token.getPaymentToken());
        this.setPaymentTokenExpiration(token.getPaymentTokenExpiration());
        this.setTwoFACode(token.getTwoFACode());
        this.setTwoFACodeExpiration(token.getTwoFACodeExpiration());

        return this;
    }
}
