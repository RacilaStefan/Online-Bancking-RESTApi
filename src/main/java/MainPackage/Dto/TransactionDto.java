package MainPackage.Dto;

import MainPackage.Domain.Transaction;
import MainPackage.EnumsAndStaticClasses.BankDetails;
import MainPackage.EnumsAndStaticClasses.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDto {

    private Long id;

    @NotNull
    @Column(length = BankDetails.IBAN_Length)
    private String toAccountIBAN;

    @NotNull
    @Column(length = BankDetails.IBAN_Length)
    private String fromAccountIBAN;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    private float amount;

    private LocalDateTime date;

    public Transaction fromDto() {
        Transaction transaction = new Transaction();

        transaction.setToAccountIBAN(this.toAccountIBAN);
        transaction.setFromAccountIBAN(this.fromAccountIBAN);
        transaction.setCurrency(this.currency);
        transaction.setAmount(this.amount);
        transaction.setDate(this.date);

        return transaction;
    }

    public TransactionDto getDto(Transaction transaction) {
        this.setId(transaction.getId());
        this.setToAccountIBAN(transaction.getToAccountIBAN());
        this.setFromAccountIBAN(transaction.getFromAccountIBAN());
        this.setCurrency(transaction.getCurrency());
        this.setAmount(transaction.getAmount());
        this.setDate(transaction.getDate());

        return this;
    }
}
