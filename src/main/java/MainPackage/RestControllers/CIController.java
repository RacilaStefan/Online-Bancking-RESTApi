package MainPackage.RestControllers;

import MainPackage.Dto.CIDto;
import MainPackage.Services.DatabaseCommunication.ModelReturnType.CIEntityModelService;
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

    private final CIEntityModelService service;

    @GetMapping()
    public CollectionModel<EntityModel<CIDto>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<CIDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
