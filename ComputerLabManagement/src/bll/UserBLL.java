package bll;

import dal.UserDAO;
import model.User;
import java.util.List;

public class UserBLL {
    private UserDAO userDAO = new UserDAO();

    public User authenticate(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }
}