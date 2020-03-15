package nl.hsleiden.service;

import com.google.inject.Inject;
import nl.hsleiden.model.BaseModel;
import nl.hsleiden.persistence.BaseDAO;

import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * @param <M>: Model
 * @author Peter van Vliet
 * @Param <D>: Dao
 */
public class BaseService<M extends BaseModel, D extends BaseDAO<M>> {
    protected D dao;

    @Inject
    public BaseService(D dao) {
        this.dao = dao;
    }

    public M requireResult(M model) {
        if (model == null) {
            throw new NotFoundException();
        }

        return model;
    }


    public M findById(int id) {
        return requireResult(dao.findById(id));
    }

    public List<M> findAll() {
        return dao.findAll();
    }

    public void create(M obj) {
        dao.create(obj);
    }

    public void delete(M obj) {
        dao.delete(obj);
    }

    public void update(M obj, int id) {
        obj.setId(id);
        dao.update(obj);
    }
}
