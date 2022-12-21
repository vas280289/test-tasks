package services;

import exceptions.EmptyFileException;
import org.junit.Before;
import org.junit.Test;
import services.TextFileService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Класс для тестирования сервиса для подсчета частот встречаемых в текстовом файле слов {@link TextFileService}
 */
public class TextFileServiceTest {
    private static final String VALID_FILE_PATH = "src/test/resources/valid_file.txt";
    private static final String EMPTY_FILE_PATH = "src/test/resources/empty_file.txt";
    private static final String INVALID_FILE_PATH = "src/test/resources/invalid_file.jpg";
    private static final String NON_EXISTING_FILE_PATH = "src/test/resources/non_existing_file.txt";

    private TextFileService service;

    @Before
    public void before() {
        service = TextFileService.getInstance();
    }

    /**
     * Позитивный сценарий
     * @throws IOException исключение при ненайденном файле или неверном формате файла
     * @throws EmptyFileException исключение при пустом файле
     */
    @Test
    public void getWordsFrequenciesInFileSuccessTest() throws IOException, EmptyFileException {
        String actual = service.getWordsFrequenciesInFile(VALID_FILE_PATH);

        assertEquals(getCorrectFrequenciesString(),actual);
    }

    /**
     * Негативный сценарий - полученное стровокове представление не соответствует логике
     * @throws IOException исключение при ненайденном файле или неверном формате файла
     * @throws EmptyFileException исключение при пустом файле
     */
    @Test
    public void getWordsFrequenciesInFileFailWrongCountTest() throws IOException, EmptyFileException {
        String actual = service.getWordsFrequenciesInFile(VALID_FILE_PATH);

        assertNotEquals(getIncorrectFrequenciesString(),actual);
    }

    /**
     * Негативный сценарий - при передаче пути к пустому файлу выбрасывается исключение
     * @throws IOException исключение при ненайденном файле или неверном формате файла
     * @throws EmptyFileException исключение при пустом файле
     */
    @Test(expected = EmptyFileException.class)
    public void getWordsFrequenciesInFileFailEmptyFileTest() throws IOException, EmptyFileException {
        service.getWordsFrequenciesInFile(EMPTY_FILE_PATH);
    }

    /**
     * Негативный сценарий - при передаче пути к файлу неверного формата выбрасывается исключение
     * @throws IOException исключение при ненайденном файле или неверном формате файла
     * @throws EmptyFileException исключение при пустом файле
     */
    @Test(expected = IOException.class)
    public void getWordsFrequenciesInFileFailInvalidFileTest() throws IOException, EmptyFileException {
        service.getWordsFrequenciesInFile(INVALID_FILE_PATH);
    }

    /**
     * Негативный сценарий - при передаче пути к отсутствующему файлу выбрасывается исключение
     * @throws IOException исключение при ненайденном файле или неверном формате файла
     * @throws EmptyFileException исключение при пустом файле
     */
    @Test(expected = IOException.class)
    public void getWordsFrequenciesInFileFailFileNotFoundTest() throws IOException, EmptyFileException {
        service.getWordsFrequenciesInFile(NON_EXISTING_FILE_PATH);
    }

    private String getCorrectFrequenciesString() {
        return "Окно - 1\n" +
                "стола - 1\n" +
                "стул. - 1\n" +
                "стола. - 1\n" +
                "Стул - 1\n" +
                "стоял - 2\n" +
                "возле - 1\n" +
                "Возле - 1";
    }

    private String getIncorrectFrequenciesString() {
        return "Окно - 1\n" +
                "стола - 1\n" +
                "стул. - 1\n" +
                "стола. - 1\n" +
                "Стул - 1\n" +
                "стоял - 1\n" +
                "возле - 1\n" +
                "Возле - 1";
    }
}
