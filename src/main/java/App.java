import model.UserDTO;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.UserService;

import java.util.Scanner;

import static util.Validator.readValidCommand;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserService(scanner, userRepository);
        while (true) {
            System.out.println("Выберете команду");
            System.out.println("""
                    \s
                     1 - Добавить пользователя
                     2 - Показать список всех пользователей
                     3 - Редактировать пользователя
                     4 - Удалить пользователя
                     5 - Завершить программу""");

            int number = readValidCommand(scanner);

            switch (number) {
                case 1:
                    userService.createUser();
                    break;
                case 2:
                    userService.getAllUsers().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Введите адрес почты пользователя,которого хотите отредактировать");
                    String email = scanner.nextLine();
                    userService.updateUser(email);
                    break;
                case 4:
                    System.out.println("Введите адрес почты пользователя,которого хотите удалить");
                    String emailToDelete = scanner.nextLine();
                    userService.deleteUser(emailToDelete);
                    break;
                case 5:
                    System.out.println("Программа завершена.");
                    return;

            }
        }
    }
}
