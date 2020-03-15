package nl.hsleiden.service;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.UnauthorizedHandler;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.model.Role;
import nl.hsleiden.model.UserModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * @author Loes
 */
@Singleton
public class AuthService implements Authenticator<BasicCredentials, UserModel>, Authorizer<UserModel>, UnauthorizedHandler {
    private UserService service;

    @Inject
    public AuthService(UserService service) {
        this.service = service;
    }

    @UnitOfWork
    @Override
    public Optional<UserModel> authenticate(BasicCredentials credentials) throws AuthenticationException {
        return service.getByCredentials(credentials.getUsername(), credentials.getPassword());
    }

    @Override
    public boolean authorize(UserModel model, String rolename) {
        return model.hasRole(Role.valueOf(rolename));
    }

    @Override
    public Response buildResponse(String prefix, String realm) {
        throw new ForbiddenException();
    }
}