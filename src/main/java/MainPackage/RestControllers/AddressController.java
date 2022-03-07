package MainPackage.RestControllers;

import MainPackage.Dto.AddressDto;
import MainPackage.Services.DatabaseCommunication.AddressDbService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ap1/v2/addresses")

@AllArgsConstructor
public class AddressController {

    private final AddressDbService service;

    @GetMapping()
    public CollectionModel<EntityModel<AddressDto>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<AddressDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
