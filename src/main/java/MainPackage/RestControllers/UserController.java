package MainPackage.RestControllers;

import MainPackage.Dto.*;
import MainPackage.Services.DatabaseCommunication.UserDbService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v2/users")

@AllArgsConstructor
public class UserController {

    private final UserDbService service;

    @GetMapping()
    public CollectionModel<EntityModel<UserDto>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<UserDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/address")
    public EntityModel<AddressDto> findAddressByUserId(@PathVariable Long id){
        return service.findAddressByUserId(id);
    }

    @GetMapping("/{id}/ci")
    public EntityModel<CIDto> findCiByUserId(@PathVariable Long id){
        return service.findCIByUserId(id);
    }

    @GetMapping("/{id}/token")
    public EntityModel<TokenDto> findTokenByUserId(@PathVariable Long id){
        return service.findTokenByUserId(id);
    }

    @GetMapping("/{id}/accounts")
    public CollectionModel<EntityModel<AccountDto>> findAccountsByUserId(@PathVariable Long id){
        return service.findAccountsByUserId(id);
    }

    @GetMapping("/{id}/account/{accountId}")
    public EntityModel<AccountDto> findAccountByIdByUserId(@PathVariable Long id, @PathVariable Long accountId){
        return service.findAccountByIdByUserId(id, accountId);
    }
}
