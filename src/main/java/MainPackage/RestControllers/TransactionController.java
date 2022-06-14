package MainPackage.RestControllers;

import MainPackage.Dto.TransactionDto;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.EntityModel.TransactionEntityModelService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Transaction Controller")
@RestController
@RequestMapping("/api/v2/transactions")

@AllArgsConstructor
public class TransactionController {

    private final TransactionEntityModelService service;

    //#######  GET ENDPOINTS  #######//

    @GetMapping()
    public CollectionModel<EntityModel<TransactionDto>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<TransactionDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    //#######  GET ENDPOINTS  #######//

    //#######  POST ENDPOINTS  #######//

    @PostMapping()
    private ResponseEntity<String> saveTransaction(@Valid @RequestBody TransactionDto transaction) throws CustomInvalidInputException {
        service.saveTransaction(transaction);

        return new ResponseEntity<>("Transaction approved", HttpStatus.CREATED);
    }

    //#######  POST ENDPOINTS  #######//
}
