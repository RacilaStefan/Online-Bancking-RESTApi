package MainPackage.Dto;

import MainPackage.Domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private Long id;
    private String country;
    private String region;
    private String city;
    private String street;
    private String number;
    private String staircase;
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
