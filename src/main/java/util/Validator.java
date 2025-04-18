package util;

import lombok.extern.slf4j.Slf4j;
import model.UserDTO;
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
}
