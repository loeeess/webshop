package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.View;
import nl.hsleiden.model.BaseModel;
import nl.hsleiden.persistence.BaseDAO;
import nl.hsleiden.service.BaseService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * A base resource is a simple resource that provides all CRUD operations on a BaseModel.
 * That is, it implements all operations for the 'id' field.
 * <p>
 * WARNING
 * It should be noted that extending from this resource does NOT provide any role-based security whatsoever.
 * Extendors should annotate their with @RolesAllowed as they see fit.
 *
 * @param <M> BaseModel
 * @param <D> BaseDao
 * @param <S> BaseService
 * @author Loes
 */
@Singleton
public class BaseResource<M extends BaseModel, D extends BaseDAO<M>, S extends BaseService<M, D>> {
    protected S service;

    @Inject
    public BaseResource(S service) {
        this.service = service;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @JsonView(View.Protected.class)
    @Produces(MediaType.APPLICATION_JSON)
    public M findById(@PathParam("id") int id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    @JsonView(View.Public.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public void update(@PathParam("id") int id, @Valid M model) {
        service.update(model, id);
    }


    @DELETE
    @Path("/{id}")
    @UnitOfWork
    @JsonView(View.Public.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") int id) {
        service.delete(service.findById(id));
    }

    @POST
    @UnitOfWork
    @JsonView(View.Public.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public void create(@Valid M model) {
        service.create(model);
    }

    @GET
    @UnitOfWork
    @JsonView(View.Protected.class)
    @Produces(MediaType.APPLICATION_JSON)
    public List<M> findAll() {
        return service.findAll();
    }
}
