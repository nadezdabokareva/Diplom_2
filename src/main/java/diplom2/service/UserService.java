package diplom2.service;

import diplom2.entity.User;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

@Story("Создание данных пользователя")
public class UserService {

    @DisplayName("Создание случайного пользователя")
    public static User generateRandomUser() {
        return User.builder()
                .password(generatePassword())
                .name(generateRandomName())
                .email(generateRandomEmail())
                .build();
    }

    @DisplayName("Создание случайного адреса почты")
    public static String generateRandomEmail() {
        return String.format("%s@%s.ru", RandomStringUtils.randomAlphabetic(6), RandomStringUtils.randomAlphabetic(6)).toLowerCase(Locale.ROOT);
    }

    @DisplayName("Создание случайного имени")
    public static String generateRandomName() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    @DisplayName("Создание случайного пароля")
    public static String generatePassword() {
        return RandomStringUtils.randomAlphabetic(12);
    }
}
