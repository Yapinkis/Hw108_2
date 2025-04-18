package repository;

import model.User;
import model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void addUser(User user);
    Optional<List<User>> getAllUsers();
    Optional<User> getUserByEmail(String email);
    void editUser(UserDTO userDTO, String email);
    boolean deleteUser(String email);
}
