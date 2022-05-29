package MainPackage.Services;

import MainPackage.Domain.User;
import MainPackage.Services.DatabaseCommunication.EntityModelType.UserDbService;
import MainPackage.Services.DatabaseCommunication.ModelReturnType.UserEntityModelService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserDbService userDbService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDbService.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return user;
    }
}
