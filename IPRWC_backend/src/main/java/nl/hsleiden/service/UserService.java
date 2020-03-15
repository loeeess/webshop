package nl.hsleiden.service;

import nl.hsleiden.model.UserModel;
import nl.hsleiden.persistence.UserDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class UserService extends BaseService<UserModel, UserDAO> {
    @Inject
    public UserService(UserDAO dao) {
        super(dao);
    }

    public Optional<UserModel> getByCredentials(String username, String password) {
        return dao.getByCredentials(username, password);
    }
}
