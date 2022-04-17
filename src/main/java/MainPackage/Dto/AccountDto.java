package MainPackage.Dto;

import MainPackage.Domain.Account;
import MainPackage.EnumsAndStaticClasses.AccountType;
import MainPackage.EnumsAndStaticClasses.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDto {
    
    private Long id;
    private String IBAN;
    private String cardNumber;
    private String pin;
    private String CVV;
    private Long balance;
    private LocalDateTime expirationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType type;


    public Account fromDto() {
        Account account = new Account();
        account.setId(this.id);
        account.setIBAN(this.IBAN);
        account.setCardNumber(this.cardNumber);
        account.setPin(this.pin);
        account.setCVV(this.CVV);
        account.setBalance(this.balance);
        account.setExpirationDate(this.expirationDate);
        account.setCurrency(this.currency);
        account.setType(this.type);

        return account;
    }

    public AccountDto getDto(Account account) {
        this.setId(account.getId());
        this.setIBAN(account.getIBAN());
        this.setCardNumber(account.getCardNumber());
        this.setPin(account.getPin());
        this.setCVV(account.getCVV());
        this.setBalance(account.getBalance());
        this.setExpirationDate(account.getExpirationDate());
        this.setCurrency(account.getCurrency());
        this.setType(account.getType());

        return this;
    }

}
