package MainPackage.Services.Utils.Interfaces;

import MainPackage.Domain.*;
import MainPackage.Dto.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.Set;

public interface IEntityModelGenerator {

    CollectionModel<EntityModel<UserDto>> generateModelFromUsers(Iterable<User> users);

    EntityModel<UserDto> generateModelFromUser(User user);

    CollectionModel<EntityModel<AddressDto>> generateModelFromAddresses(Iterable<Address> addresses);

    EntityModel<AddressDto> generateModelFromAddress(Address address);

    CollectionModel<EntityModel<CIDto>> generateModelFromCIs(Iterable<CI> CIs);

    EntityModel<CIDto> generateModelFromCi(CI ci);

    EntityModel<TokenDto> generateModelFromToken(Token token);

    CollectionModel<EntityModel<AccountDto>> generateModelFromAccounts(Set<Account> accounts);

    CollectionModel<EntityModel<AccountDto>> generateModelFromAccounts(Iterable<Account> accounts);

    EntityModel<AccountDto> generateModelFromAccount(Account account);
}
