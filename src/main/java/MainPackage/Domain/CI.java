package MainPackage.Domain;

import MainPackage.Dto.CIDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String CNP;

    @NotNull
    private String series;

    @NotNull
    private String number;

    @NotNull
    private LocalDateTime expirationDate;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
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
