package nl.hsleiden.persistence;

import nl.hsleiden.model.BaseModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * BaseDAO is a simple implementation of the CRUD operations.
 *
 * @param <T>
 * @author Loes
 */
public class BaseDAO<T extends BaseModel> {
    private final Class<T> type;
    private final SessionFactory sessionFactory;

    public BaseDAO(Class<T> type, SessionFactory sessionFactory) {
        this.type = type;
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public T findById(int id) {
        return currentSession().get(type, id);
    }

    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        criteriaQuery.from(type);
        return currentSession().createQuery(criteriaQuery).getResultList();

    }

    public void create(T obj) {
        currentSession().save(obj);
    }

    public void delete(T obj) {
        currentSession().delete(obj);
    }

    public void update(T obj) {
        currentSession().update(obj);
    }
}