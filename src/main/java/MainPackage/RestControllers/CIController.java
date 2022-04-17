package MainPackage.RestControllers;

import MainPackage.Dto.CIDto;
import MainPackage.Dto.UserDto;
import MainPackage.Services.DatabaseCommunication.CIDbService;
import MainPackage.Services.DatabaseCommunication.UserDbService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ap1/v2/cis")

@AllArgsConstructor
public class CIController {

    private final CIDbService service;

    @GetMapping()
    public CollectionModel<EntityModel<CIDto>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<CIDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
