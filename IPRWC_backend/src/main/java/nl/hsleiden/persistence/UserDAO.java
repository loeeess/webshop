package nl.hsleiden.persistence;

import com.google.inject.Inject;
import nl.hsleiden.model.UserModel;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class UserDAO extends BaseDAO<UserModel> {
    Finder<UserModel, UserDAO> finder;

    @Inject
    public UserDAO(SessionFactory factory) {
        super(UserModel.class, factory);
        this.finder = new Finder(UserModel.class, this);
    }

    public Optional<UserModel> getByCredentials(String username, String password) {
        return this.finder.findBy(
                (criteriaBuilder, criteriaQuery, root) ->
                        criteriaQuery.where(criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("email"), username),
                            criteriaBuilder.equal(root.get("password"), password)
                        )),
                Query::uniqueResultOptional
        );
    }
}