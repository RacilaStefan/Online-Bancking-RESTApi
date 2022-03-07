package MainPackage.Dto;

import MainPackage.Domain.Token;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TokenDto {

    private Long id;
    private UUID token1;
    private LocalDateTime expDate1;
    private UUID token2;
    private LocalDateTime expDate2;

    public Token fromDto() {
        Token token = new Token();
        token.setId(this.id);
        token.setToken1(this.token1);
        token.setExpDate1(this.expDate1);
        token.setToken2(this.token2);
        token.setExpDate2(this.expDate2);

        return token;
    }

    public TokenDto getDto(Token token) {
        this.setId(token.getId());
        this.setToken1(token.getToken1());
        this.setExpDate1(token.getExpDate1());
        this.setToken2(token.getToken2());
        this.setExpDate2(token.getExpDate2());

        return this;
    }
}
