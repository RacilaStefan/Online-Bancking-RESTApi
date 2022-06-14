package MainPackage.Services.Utils.Implementations;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CurrentTime {

    public static LocalDate today;
    public static LocalDate tomorrow;
    public static LocalDate afterOneYear;
    public static LocalDate afterTwoYears;
    public static LocalDate afterThreeYears;

    public static void init() {
        today = LocalDate.now();
        tomorrow = today.plusDays(1);
        afterOneYear = today.plusYears(1);
        afterTwoYears = today.plusYears(2);
        afterThreeYears = today.plusYears(3);
    }
}
