package MainPackage.RestControllers;

import MainPackage.Dto.*;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.Utils.Implementations.RegisterService;
import MainPackage.Services.DatabaseCommunication.UserDbService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v2/users")

@AllArgsConstructor
public class UserController {

    private final UserDbService userService;
    private final RegisterService registerService;

    //#######  GET ENDPOINTS  #######//

    @GetMapping()
    public CollectionModel<EntityModel<UserDto>> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<UserDto> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{id}/address")
    public EntityModel<AddressDto> findAddressByUserId(@PathVariable Long id){
        return userService.findAddressByUserId(id);
    }

    @GetMapping("/{id}/ci")
    public EntityModel<CIDto> findCiByUserId(@PathVariable Long id){
        return userService.findCIByUserId(id);
    }

    @GetMapping("/{id}/token")
    public EntityModel<TokenDto> findTokenByUserId(@PathVariable Long id){
        return userService.findTokenByUserId(id);
    }

    @GetMapping("/{id}/accounts")
    public CollectionModel<EntityModel<AccountDto>> findAccountsByUserId(@PathVariable Long id){
        return userService.findAccountsByUserId(id);
    }

    @GetMapping("/{id}/account/{accountId}")
    public EntityModel<AccountDto> findAccountByIdByUserId(@PathVariable Long id, @PathVariable Long accountId){
        return userService.findAccountByIdByUserId(id, accountId);
    }

    //#######  GET ENDPOINTS  #######//

    //#######  POST ENDPOINTS  #######//

    @PostMapping()
    private ResponseEntity<String> save(@RequestBody UserDto user) throws CustomInvalidInputException {
        registerService.registerUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    //#######  POST ENDPOINTS  #######//
}
