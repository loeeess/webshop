package nl.hsleiden.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import nl.hsleiden.model.ProductModel;
import nl.hsleiden.persistence.ProductDAO;
import nl.hsleiden.service.ProductService;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
@Path("/product")
public class ProductResource extends BaseResource<ProductModel, ProductDAO, ProductService> {
    @Inject
    public ProductResource(ProductService service) { super(service); }
}
