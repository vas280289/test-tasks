package services;

import exceptions.EmptyFileException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Сервис для обработки ввода из консоли
 */
public class IOService {
    private static final String EXIT_SEQUENCE = "exit";
    private static final String EXIT_MESSAGE = "Выход из приложения";
    private static final String FILE_ERROR_MESSAGE = "Файл не найден или не может быть открыт";
    private static final String UNKNOWN_ERROR_MESSAGE = "Неизвестная ошибка";
    private static final String INPUT_GREETING_MESSAGE = "Введите путь к файлу, для выхода введите \"exit\":";
    private static final String EMPTY_FILE_ERROR_MESSAGE = "Текстовый файл не содержит слов";
    private static final IOService ioService = new IOService();

    private final Scanner scanner = new Scanner(System.in);

    private IOService() {

    }

    /**
     * Получить экземпляр сервиса
     * @return экземпляр сервиса
     */
    public static IOService getInstance() {
        return ioService;
    }

    /**
     * Обработка инпутов из консоли в бесконечном цикле
     */
    public void handleInputs() {
        while (true) {
            System.out.println(INPUT_GREETING_MESSAGE);

            String input = scanner.nextLine();

            System.out.println(handleInput(input));

            if (EXIT_SEQUENCE.equals(input)) {
                break;
            }
        }
    }

    private String handleInput(String input) {
        if (EXIT_SEQUENCE.equals(input)) {
            return EXIT_MESSAGE;
        }

        try {
            return (TextFileService.getInstance().getWordsFrequenciesInFile(input));
        } catch (IOException e) {
            return FILE_ERROR_MESSAGE;
        } catch (EmptyFileException e) {
            return EMPTY_FILE_ERROR_MESSAGE;
        } catch (Exception e) {
            return UNKNOWN_ERROR_MESSAGE;
        }
    }
}
