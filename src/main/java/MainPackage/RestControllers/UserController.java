package MainPackage.RestControllers;

import MainPackage.Dto.*;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.EntityModel.UserEntityModelService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.support.Repositories;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User Controller")

@RestController
@RequestMapping("/api/v2/users")

@AllArgsConstructor
public class UserController {

    private final UserEntityModelService service;

    //#######  GET ENDPOINTS  #######//

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

    @GetMapping("/{userId}/account/{accountId}")
    public EntityModel<AccountDto> findAccountByIdByUserId(@PathVariable Long userId, @PathVariable Long accountId){
        return service.findAccountByIdByUserId(userId, accountId);
    }

    //#######  GET ENDPOINTS  #######//

    //#######  POST ENDPOINTS  #######//

    @PostMapping()
    private ResponseEntity<String> saveUser(@RequestBody UserDto user) throws CustomInvalidInputException {
        service.saveUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PostMapping("/{id}/accounts")
    private ResponseEntity<String> saveAccount(@PathVariable Long id, @RequestBody AccountDto account) throws CustomInvalidInputException {
        service.saveAccountByUserId(id, account);
        return new ResponseEntity<>("Account created", HttpStatus.CREATED);
    }

    //#######  POST ENDPOINTS  #######//

    //#######  PUT ENDPOINTS  #######//

    @PutMapping("/{id}")
    private ResponseEntity<String> saveUser(@PathVariable Long id, @RequestBody UserDto user) throws CustomInvalidInputException {
        service.saveUser(user, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //#######  PUT ENDPOINTS  #######//

    //#######  DELETE ENDPOINTS  #######//

    @DeleteMapping("/{userId}/account/{accountId}")
    public ResponseEntity<String> deleteAccountByIdByUserId(@PathVariable Long userId, @PathVariable Long accountId){
        service.deleteAccountByIdByUserId(userId, accountId);
        return new ResponseEntity<>("Bank account deleted with success.", HttpStatus.OK);
    }

    //#######  DELETE ENDPOINTS  #######//
}
