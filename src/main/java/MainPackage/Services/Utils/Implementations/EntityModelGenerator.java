package MainPackage.Services.Utils.Implementations;

import MainPackage.Domain.*;
import MainPackage.Dto.*;
import MainPackage.RestControllers.AccountController;
import MainPackage.RestControllers.AddressController;
import MainPackage.RestControllers.CIController;
import MainPackage.RestControllers.UserController;
import MainPackage.Services.Utils.Interfaces.IEntityModelGenerator;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class EntityModelGenerator implements IEntityModelGenerator {

    public CollectionModel<EntityModel<UserDto>> generateModelFromUsers(Iterable<User> users) {
        return CollectionModel.of(
                StreamSupport.stream(users.spliterator(), false).map(
                        this::generateModelFromUser
                ).collect(Collectors.toSet()),
                linkTo(methodOn(UserController.class).findAll()).withRel("users"));
    }

    public EntityModel<UserDto> generateModelFromUser(User user) {
        ArrayList<Link> links = new ArrayList<>();
        AtomicInteger index = new AtomicInteger();

        links.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
        links.add(linkTo(methodOn(UserController.class).findAccountsByUserId(user.getId())).withRel("accounts"));
        links.addAll(user.getAccounts().stream().map(
                account -> linkTo(methodOn(UserController.class)
                        .findAccountByIdByUserId(user.getId(), account.getId()))
                        .withRel("account" + index.getAndIncrement())
        ).collect(Collectors.toList()));
        links.add(linkTo(methodOn(UserController.class).findAddressByUserId(user.getId())).withRel("address"));
        links.add(linkTo(methodOn(UserController.class).findCiByUserId(user.getId())).withRel("ci"));
        links.add(linkTo(methodOn(UserController.class).findTokenByUserId(user.getId())).withRel("token"));

        return EntityModel.of(new UserDto().getDto(user), links);
    }

    public CollectionModel<EntityModel<AddressDto>> generateModelFromAddresses(Iterable<Address> addresses, Boolean isParent) {
        return CollectionModel.of(
                StreamSupport.stream(addresses.spliterator(), false).map(
                        address -> generateModelFromAddress(address, isParent)
                ).collect(Collectors.toSet()),
                linkTo(methodOn(AddressController.class).findAll()).withRel("addresses"));
    }

    public EntityModel<AddressDto> generateModelFromAddress(Address address, Boolean isParent) {
        if (isParent)
            return EntityModel.of(new AddressDto().getDto(address),
                    linkTo(methodOn(AddressController.class).findById(address.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).findById(address.getUser().getId())).withRel("user"));

        return EntityModel.of(new AddressDto().getDto(address),
                linkTo(methodOn(UserController.class).findAddressByUserId(address.getUser().getId())).withSelfRel(),
                linkTo(methodOn(AddressController.class).findAll()).withRel("addresses"));
    }

    public CollectionModel<EntityModel<CIDto>> generateModelFromCIs(Iterable<CI> CIs, Boolean isParent) {
        return null;
    }

    public EntityModel<CIDto> generateModelFromCi(CI ci, Boolean isParent) {
        if (isParent)
            return EntityModel.of(new CIDto().getDto(ci),
                    linkTo(CIController.class).withSelfRel());

        return EntityModel.of(new CIDto().getDto(ci),
                linkTo(methodOn(UserController.class).findCiByUserId(ci.getUser().getId())).withSelfRel());
    }

    public EntityModel<TokenDto> generateModelFromToken(Token token) {
        return EntityModel.of(new TokenDto().getDto(token),
                linkTo(methodOn(UserController.class).findTokenByUserId(token.getUser().getId())).withSelfRel());
    }

    public CollectionModel<EntityModel<AccountDto>> generateModelFromAccounts(Set<Account> accounts, Boolean isParent) {
        return CollectionModel.of(accounts.stream().map(
                account -> generateModelFromAccount(account, isParent)
                ).collect(Collectors.toSet()),
                linkTo(methodOn(UserController.class)
                        .findAccountsByUserId(accounts
                                .stream()
                                .findAny()
                                .orElseThrow().getUser().getId())).withRel("userAccounts"));
    }

    public EntityModel<AccountDto> generateModelFromAccount(Account account, Boolean isParent) {
        if (isParent)
            return EntityModel.of(new AccountDto().getDto(account),
                    linkTo(AccountController.class).withSelfRel());

        return EntityModel.of(new AccountDto().getDto(account),
                linkTo(methodOn(UserController.class)
                        .findAccountByIdByUserId(account.getUser().getId(), account.getId())).withSelfRel());
    }
}
