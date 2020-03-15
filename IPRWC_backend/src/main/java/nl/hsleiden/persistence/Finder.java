package nl.hsleiden.persistence;

import nl.hsleiden.model.BaseModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * A Finder can be used to `find`
 * all elements of a database table,
 * using a more complex query.
 *
 * All setup logic for types needed to create such a complex query are setup trough this class,
 * the implementation is given to this class trough the TriFunction, which this class then uses to execute the query.
 * For execution of the query, a QueryExtractor must be provided to this class, so the caller can decide
 * what the query should result in.
 * @param <T> Model
 * @param <D> Dao
 */
@Singleton
public class Finder<T extends BaseModel, D extends BaseDAO<T>> {
    D dao;
    Class<T> type;

    public Finder(Class<T> type, D dao) {
        this.type = type;
        this.dao = dao;
    }

    public <R> R findBy(TriFunction<CriteriaBuilder, CriteriaQuery<?>, Root<?>> buildQuery, QueryExtractor<T, R> extractor) {
        Session session = this.dao.currentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);

        buildQuery.apply(criteriaBuilder, criteriaQuery, root);

        Query<T> q = session.createQuery(criteriaQuery);

        return extractor.extract(q);
    }
}