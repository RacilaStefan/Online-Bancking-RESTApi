package MainPackage.RestControllers;

import MainPackage.Dto.AddressDto;
import MainPackage.Services.EntityModel.AddressEntityModelService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Address Controller")

@RestController
@RequestMapping("/ap1/v2/addresses")

@AllArgsConstructor
public class AddressController {

    private final AddressEntityModelService service;

    //#######  GET ENDPOINTS  #######//

    @GetMapping()
    public CollectionModel<EntityModel<AddressDto>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<AddressDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    //#######  GET ENDPOINTS  #######//
}
