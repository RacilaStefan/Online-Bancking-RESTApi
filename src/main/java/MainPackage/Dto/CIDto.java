package MainPackage.Dto;

import MainPackage.Domain.CI;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CIDto {

    private Long id;

    @NotNull
    private String CNP;

    @NotNull
    private String series;

    @NotNull
    private String number;

    @NotNull
    private LocalDateTime expirationDate;

    public CI fromDto() {
        CI ci = new CI();
        ci.setId(this.id);
        ci.setCNP(this.CNP);
        ci.setSeries(this.series);
        ci.setNumber(this.number);
        ci.setExpirationDate(this.expirationDate);

        return ci;
    }

    public CIDto getDto(CI ci) {
        this.setId(ci.getId());
        this.setCNP(ci.getCNP());
        this.setSeries(ci.getSeries());
        this.setNumber(ci.getNumber());
        this.setExpirationDate(ci.getExpirationDate());

        return this;
    }
}
