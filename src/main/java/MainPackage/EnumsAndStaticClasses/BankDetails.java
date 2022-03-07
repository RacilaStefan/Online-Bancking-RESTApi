package MainPackage.EnumsAndStaticClasses;

import java.util.Map;

public class BankDetails {

    public final static String CountryCode = "RO";
    public final static String Identifier = "PRNK"; //PRO King (Predictible Responsible Online banKing)
    public final static int IBAN_Length = 24;
    public final static int CardNumberLength = 16;
    public final static String visaPrefix = "4";
    public final static Map<Currency, String> currencyID =
            Map.of( Currency.EUR, "10",
                    Currency.USD, "11",
                    Currency.RON, "12",
                    Currency.GBP, "13");
    public final static Map<AccountType, String> accountTypeID =
            Map.of( AccountType.CURRENT_ACCOUNT, "100",
                    AccountType.SAVINGS_ACCOUNT, "101",
                    AccountType.RECURRING_DEPOSIT_ACCOUNT, "103",
                    AccountType.FIXED_DEPOSIT_ACCOUNT, "104");

}
