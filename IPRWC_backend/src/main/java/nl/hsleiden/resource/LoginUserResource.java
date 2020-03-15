package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.View;
import nl.hsleiden.model.CredentialsModel;
import nl.hsleiden.model.UserModel;
import nl.hsleiden.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
@Path("/login")
public class LoginUserResource {
    private final UserService service;

    @Inject
    public LoginUserResource(UserService service) {
        this.service = service;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel get(@Valid CredentialsModel credentials) {
        return service.getByCredentials(credentials.getUsername(), credentials.getPassword())
                .orElseThrow(() -> new NotAuthorizedException("Required credentials"));
    }
}
