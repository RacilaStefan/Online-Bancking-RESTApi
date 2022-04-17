package MainPackage.Services.Utils.Implementations;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CurrentTime {

    public static LocalDateTime today;
    public static LocalDateTime tomorrow;
    public static LocalDateTime afterOneYear;
    public static LocalDateTime afterTwoYears;
    public static LocalDateTime afterThreeYears;

    public static void init() {
        today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        tomorrow = today.plusDays(1);
        afterOneYear = today.plusYears(1);
        afterTwoYears = today.plusYears(2);
        afterThreeYears = today.plusYears(3);
    }
}
