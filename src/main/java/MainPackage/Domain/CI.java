package MainPackage.Domain;

import MainPackage.Dto.CIDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "CI",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"CNP", "number"}),
            })

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "[0-9]{13}")
    private String CNP;

    @NotNull
    @Pattern(regexp = "[A-Z]{2}")
    private String series;

    @NotNull
    @Pattern(regexp = "[0-9]{6}")
    private String number;

    @NotNull
    private LocalDate expirationDate;

    @JsonBackReference
    @OneToOne (mappedBy = "ci")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CI ci = (CI) o;

        if (!Objects.equals(id, ci.id)) return false;
        if (!Objects.equals(CNP, ci.CNP)) return false;
        if (!Objects.equals(series, ci.series)) return false;
        return Objects.equals(number, ci.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, CNP, series, number, expirationDate);
    }

    @Override
    public String toString() {
        return "CI{" +
                "id=" + id +
                ", CNP='" + CNP + '\'' +
                ", series='" + series + '\'' +
                ", number='" + number + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
