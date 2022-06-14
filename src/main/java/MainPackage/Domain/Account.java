package MainPackage.Domain;

import MainPackage.EnumsAndStaticClasses.AccountType;
import MainPackage.EnumsAndStaticClasses.BankDetails;
import MainPackage.EnumsAndStaticClasses.Currency;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table (name = "Account",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"IBAN", "cardNumber"}) })

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = BankDetails.IBAN_Length)
    private String IBAN;

    @NotNull
    @Column(length = BankDetails.CardNumberLength)
    private String cardNumber;

    @NotNull
    @Pattern(regexp = "[0-9]{4}")
    private String pin;

    @NotNull
    private String CVV;

    @NotNull
    private float balance;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @JsonBackReference
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (balance != account.balance) return false;
        if (!Objects.equals(id, account.id)) return false;
        if (!Objects.equals(IBAN, account.IBAN)) return false;
        if (!Objects.equals(currency, account.currency)) return false;
        if (!Objects.equals(cardNumber, account.cardNumber)) return false;
        if (!Objects.equals(pin, account.pin)) return false;
        if (type != account.type) return false;
        if (!Objects.equals(CVV, account.CVV)) return false;
        return Objects.equals(expirationDate, account.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                IBAN,
                cardNumber,
                pin,
                CVV,
                balance,
                expirationDate,
                currency,
                type);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", IBAN='" + IBAN + '\'' +
                ", money='" + balance + '\'' +
                ", currency='" + currency + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", type=" + type +
                ", CCV='" + CVV + '\'' +
                ", expireDate=" + expirationDate +
                '}';
    }
}
