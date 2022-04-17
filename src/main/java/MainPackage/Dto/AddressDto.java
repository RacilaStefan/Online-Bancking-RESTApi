package MainPackage.Dto;

import MainPackage.Domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AddressDto {

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


    public Address fromDto() {
        Address address = new Address();
        address.setId(this.id);
        address.setCountry(this.country);
        address.setRegion(this.region);
        address.setCity(this.city);
        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setStaircase(this.staircase);
        address.setApartment(this.apartment);

        return address;
    }

    public AddressDto getDto(Address address) {
        this.setId(address.getId());
        this.setCountry(address.getCountry());
        this.setRegion(address.getRegion());
        this.setCity(address.getCity());
        this.setStreet(address.getStreet());
        this.setNumber(address.getNumber());
        this.setStaircase(address.getStaircase());
        this.setApartment(address.getApartment());

        return this;
    }
}
