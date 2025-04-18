package service;

import lombok.extern.slf4j.Slf4j;
import model.User;
import model.UserDTO;
import repository.UserRepository;
import util.Mapper;
import util.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
public class UserService {
    private final Scanner scanner;
    private final UserRepository userRepository;
    public UserService(Scanner scanner, UserRepository userRepository) {
        this.scanner = scanner;
        this.userRepository = userRepository;
    }
    public void createUser() {
        System.out.println("Введите имя пользователя");
        System.out.println("Допустимое число символов ввода от 3 до 100 символов");
        String name = scanner.nextLine();

        System.out.println("Введите почту пользователя");
        System.out.println("Почта должна содержать символа @");
        String email = scanner.nextLine();

        System.out.println("Введите возраст пользователя");
        System.out.println("Допустимый возраст пользователя от 8 до 99 лет");
        Integer age = scanner.nextInt();
        scanner.nextLine();

        UserDTO userDTO = UserDTO.builder().name(name).email(email).age(age).build();;
        if (Validator.validateUser(userDTO)) {
            User user = Mapper.toUser(userDTO);
            userRepository.addUser(user);
        }
    }

    public List<UserDTO> getAllUsers() {
        Optional<List<User>> optionalUsers = userRepository.getAllUsers();
        if (optionalUsers.isPresent()) {
            log.info("Предоставлен список пользователей");
            return optionalUsers.get().stream()
                    .map(Mapper::toUserDTO)
                    .collect(Collectors.toList());
        } else {
            log.info("Пользователей в базе данных не обнаружено");
            return Collections.emptyList();
        }
    }

    public void updateUser(String email) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            System.out.println("Введите параметры которые необходимо изменить с учётом требований валидации");
            System.out.println("Имя");
            String newName = scanner.nextLine();
            System.out.println("Почту");
            String newEmail = scanner.nextLine();
            System.out.println("Возраст");
            Integer newAge = scanner.nextInt();
            scanner.nextLine();
            UserDTO userDTO = UserDTO.builder().name(newName).email(newEmail).age(newAge).build();
            if (Validator.validateUser(userDTO)) {
                userRepository.editUser(userDTO,email);
                log.info("Пользователь обновлён");
            }
        } else {
            log.info("Пользователь с указанным email не обнаружен в базе данных");
        }
    }

    public void deleteUser(String email) {
        if (userRepository.deleteUser(email)) {
            log.info("Пользователь был удалён");
        } else {
            log.info("Возникла ошибка при удалении пользователя");
        }
    }

}
