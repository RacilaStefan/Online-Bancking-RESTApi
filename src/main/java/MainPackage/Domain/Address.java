package MainPackage.Domain;


import MainPackage.Dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table (name = "Address")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z \\-']{2,100}")
    private String country;

    @Pattern(regexp = "[A-Z][a-zA-Z \\-']{2,100}")
    private String region;

    @Pattern(regexp = "[A-Z][a-zA-Z \\-']{2,100}")
    private String city;

    @Pattern(regexp = "[A-Z][a-zA-Z \\-']{2,100}")
    private String street;

    @Pattern(regexp = "[A-Za-z0-9\\-]{1,10}")
    private String number;

    @Pattern(regexp = "[A-Za-z0-9\\-]{1,10}")
    private String staircase;
    
    @Pattern(regexp = "[A-Za-z0-9\\-]{1,10}")
    private String apartment;

    @JsonBackReference
    @OneToOne (mappedBy = "address")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!Objects.equals(id, address.id)) return false;
        if (!Objects.equals(country, address.country)) return false;
        if (!Objects.equals(region, address.region)) return false;
        if (!Objects.equals(city, address.city)) return false;
        if (!Objects.equals(street, address.street)) return false;
        if (!Objects.equals(number, address.number)) return false;
        if (!Objects.equals(staircase, address.staircase)) return false;
        return Objects.equals(apartment, address.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, region, city, street, number, staircase, apartment);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", staircase='" + staircase + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }

    public boolean isEmpty() {

        return id != null &&
                country != null &&
                region != null &&
                city != null &&
                street != null &&
                number != null &&
                staircase != null &&
                apartment != null &&
                user != null;
    }
}
