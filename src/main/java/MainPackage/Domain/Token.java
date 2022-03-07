package MainPackage.Domain;

import MainPackage.Dto.TokenDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table (name = "Token",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"token1", "token2"}) })

@Getter
@Setter
@NoArgsConstructor
public class Token {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private UUID token1;

    @NotNull
    private LocalDateTime expDate1;

    @NotNull
    private UUID token2;

    @NotNull
    private LocalDateTime expDate2;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (!Objects.equals(id, token.id)) return false;
        if (!Objects.equals(token1, token.token1)) return false;
        if (!Objects.equals(expDate1, token.expDate1)) return false;
        return Objects.equals(token2, token.token2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token1, expDate1, token2, expDate2);
    }

    @Override
    public String toString() {
        return "TokenTable{" +
                "id=" + id +
                ", token1=" + token1 +
                ", token2=" + token2 +
                '}';
    }
}
