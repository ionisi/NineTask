package mine.spring.dao;

import mine.spring.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> getAll();
    Optional<User> getByEmail(String email);
    User getById(int id);
    void save(User user);
    void update(int id, User user);
    void delete(int id);
}
