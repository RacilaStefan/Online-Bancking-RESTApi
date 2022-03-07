package MainPackage.Services.Utils.Interfaces;

import MainPackage.Domain.*;
import MainPackage.Dto.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.Set;

public interface IEntityModelGenerator {

    CollectionModel<EntityModel<UserDto>> generateModelFromUsers(Iterable<User> users);

    EntityModel<UserDto> generateModelFromUser(User user);

    CollectionModel<EntityModel<AddressDto>> generateModelFromAddresses(Iterable<Address> addresses, Boolean isParent);

    EntityModel<AddressDto> generateModelFromAddress(Address address, Boolean isParent);

    CollectionModel<EntityModel<CIDto>> generateModelFromCIs(Iterable<CI> CIs, Boolean isParent);

    EntityModel<CIDto> generateModelFromCi(CI ci, Boolean isParent);

    EntityModel<TokenDto> generateModelFromToken(Token token);

    CollectionModel<EntityModel<AccountDto>> generateModelFromAccounts(Set<Account> accounts, Boolean isParent);

    EntityModel<AccountDto> generateModelFromAccount(Account account, Boolean isParent);
}
