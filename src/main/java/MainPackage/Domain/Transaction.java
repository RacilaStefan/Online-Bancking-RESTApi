package MainPackage.Domain;

import MainPackage.EnumsAndStaticClasses.BankDetails;
import MainPackage.EnumsAndStaticClasses.Currency;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = BankDetails.IBAN_Length)
    private String toAccountIBAN;

    @NotNull
    @Column(length = BankDetails.IBAN_Length)
    private String fromAccountIBAN;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    @Min(value = 2, message = "The amount is too small.")
    private float amount;

    @NotNull
    private LocalDateTime date;
}
