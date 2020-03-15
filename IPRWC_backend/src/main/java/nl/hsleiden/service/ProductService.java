package nl.hsleiden.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import nl.hsleiden.model.ProductModel;
import nl.hsleiden.persistence.ProductDAO;

import java.util.List;

@Singleton
public class ProductService extends BaseService<ProductModel, ProductDAO> {
    @Inject
    public ProductService(ProductDAO dao) {
        super(dao);
    }

    public List<ProductModel> findPriceLargerThan(double price) {
        return this.dao.findPriceLargerThan(price);
    }

    public List<ProductModel> findPriceSmallerThan(double price) {
        return this.dao.findPriceSmallerThan(price);
    }
}
