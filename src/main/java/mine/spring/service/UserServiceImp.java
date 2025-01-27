package mine.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mine.spring.dao.UserDAO;
import mine.spring.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> index() {
        return userDAO.index();
    }

    @Override
    @Transactional
    public Optional<User> showEmail(String email) {
        return userDAO.showEmail(email);
    }

    @Override
    @Transactional
    public User show(int id) {
        return userDAO.show(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        userDAO.update(id, user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDAO.delete(id);
    }
}