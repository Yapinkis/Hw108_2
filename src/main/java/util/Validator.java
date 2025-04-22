package util;

import lombok.extern.slf4j.Slf4j;
import model.UserDTO;

import java.util.Scanner;

@Slf4j
public class Validator {
    public static boolean validateUser(UserDTO userDTO) {
        if (userDTO.getName().length() < 3 || userDTO.getName().length() > 100) {
            log.info("Имя пользователя должно содержать от 3 до 100 символов");
            return false;
        }
        if (!userDTO.getEmail().contains("@")) {
            log.info("Строка не соответствует формату Email и не содержит символа @");
            return false;
        }
        if (userDTO.getAge() < 8 || userDTO.getAge() > 99) {
            log.info("Возраст пользователя " + userDTO.getAge() +
                    " не соответствует диапазону от 8 до 99 лет");
            return false;
        }
        System.out.println("Пользователь прошёл валидацию");
        return true;
    }

    public static int readValidCommand(Scanner scanner) {
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Пожалуйста,введите число от 1 до 5.");
                scanner.nextLine();
                continue;
            }
            int number = scanner.nextInt();
            scanner.nextLine();
            if (number < 1 || number > 5) {
                System.out.println("Число вне допустимого диапазона.Попробуйте снова.");
                continue;
            }
            return number;
        }
    }
}
