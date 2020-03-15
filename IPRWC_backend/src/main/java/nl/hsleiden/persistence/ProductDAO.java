package nl.hsleiden.persistence;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import nl.hsleiden.model.ProductModel;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

@Singleton
public class ProductDAO extends BaseDAO<ProductModel> {
    Finder<ProductModel, ProductDAO> finder;

    @Inject
    public ProductDAO(SessionFactory factory) {
        super(ProductModel.class, factory);
        this.finder = new Finder(ProductModel.class, this);
    }

    public List<ProductModel> findPriceLargerThan(double price) {
        return this.finder.findBy((
                (criteriaBuilder, criteriaQuery, root) ->
                        criteriaQuery.where(criteriaBuilder.greaterThan(root.get("price"), price))
                ),
                Query::list
        );
    }

    public List<ProductModel> findPriceSmallerThan(double price) {
        return this.finder.findBy((
                        (criteriaBuilder, criteriaQuery, root) ->
                                criteriaQuery.where(criteriaBuilder.lessThan(root.get("price"), price))
                ),
                Query::list
        );
    }
}
