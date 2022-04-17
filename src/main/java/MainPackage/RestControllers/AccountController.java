package MainPackage.RestControllers;

import MainPackage.Domain.Account;
import MainPackage.Dto.AccountDto;
import MainPackage.Dto.AddressDto;
import MainPackage.Services.DatabaseCommunication.AccountDbService;
import MainPackage.Services.DatabaseCommunication.AddressDbService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/accounts")

@AllArgsConstructor
public class AccountController {

    private final AccountDbService service;

    @GetMapping()
    public CollectionModel<EntityModel<AccountDto>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<AccountDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
