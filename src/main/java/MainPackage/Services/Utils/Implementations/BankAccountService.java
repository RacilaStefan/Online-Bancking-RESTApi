package MainPackage.Services.Utils.Implementations;

import MainPackage.EnumsAndStaticClasses.AccountType;
import MainPackage.EnumsAndStaticClasses.BankDetails;
import MainPackage.EnumsAndStaticClasses.Currency;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@AllArgsConstructor
@Service
public class BankAccountService {

    public String generateIBAN() {
        String IBAN = "";
        IBAN += BankDetails.Identifier;
        IBAN += generateBBAN();
        IBAN += BankDetails.CountryCode;
        IBAN += "00";
        String checksum = checkSum(IBAN);
        IBAN = IBAN.substring(0, IBAN.length() - 4);
        IBAN = checksum + IBAN;
        IBAN = BankDetails.CountryCode + IBAN;

        return IBAN;
    }

    private String generateBBAN() {
        StringBuilder BBAN = new StringBuilder();

        for (int i = 0; i < 16; i++) {
           BBAN.append((int) Math.floor(Math.random() * 9));
        }

        return BBAN.toString();
    }

    private String checkSum(String IBAN) {
        String intIBAN = getIntegerReprezentationFromIBAN(IBAN);

        int remainder = new BigInteger(intIBAN).mod(BigInteger.valueOf(97)).intValue();
        String result = Integer.toString(98 - remainder);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }

    public String getIntegerReprezentationFromIBAN(String IBAN) {

        StringBuilder intIBAN = new StringBuilder();
        for (int i = 0; i < IBAN.length(); i++) {
            if (Character.isDigit(IBAN.charAt(i))) {
                intIBAN.append(IBAN.charAt(i));
            } else {
                intIBAN.append((int) IBAN.charAt(i) - 55);
            }
        }

        return intIBAN.toString();
    }

    public String generateCCV() {
        //TODO: mai mult research ptr ccv daca mai este timp
        int ccv = (int) (10 + Math.random() * 990);
        if (ccv < 100)
            return "0" + ccv;

        return Integer.toString(ccv);
    }

    public String generatePIN() {
        StringBuilder pin = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int nr = (int) Math.floor(Math.random() * 9);
            if (pin.toString().contains(Integer.toString(nr))) {
                i--;
            } else {
                pin.append(nr);
            }
        }

        return pin.toString();
    }

    public String generateCardNumber(Currency currency, AccountType type) {

        StringBuilder cardNumber = new StringBuilder();
        cardNumber.append(BankDetails.visaPrefix);
        cardNumber.append(BankDetails.currencyID.get(currency));
        cardNumber.append(BankDetails.accountTypeID.get(type));

        for (int i = 0; i < 9; i++) {
            cardNumber.append((int) Math.floor(Math.random() * 9));
        }

        cardNumber.append(cardNumberCheckSum(cardNumber.toString()));
        return cardNumber.toString();
    }

    public String cardNumberCheckSum(String cardNr) {

        int sum = 0;
        for (int i = 0; i < 15; i++) {

            int digit = Integer.parseInt(String.valueOf(cardNr.charAt(i)));

            if (i % 2 == 0) {
                int nr = digit * 2;
                if (nr > 9) {
                    sum += nr / 10 + nr % 10;
                } else {
                    sum += nr;
                }
            } else {
                sum += digit;
            }
        }

        int remainder = (sum % 10 == 0) ? 0 : 10 - (sum % 10);

        return Integer.toString(remainder);
    }
}
