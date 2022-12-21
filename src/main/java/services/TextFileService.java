package services;

import exceptions.EmptyFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс сервиса для подсчета частот встречаемых в текстовом файле слов
 */
public class TextFileService {
    private static final TextFileService instance = new TextFileService();

    private static final String DELIMITERS_REGEX = "[\\s\\n]";
    private static final String LINES_DELIMITER = "\n";
    private static final String WORD_FREQ_DELIMITER = " - ";

    private TextFileService() {

    }

    /**
     * Получить экземпляр сервиса
     * @return экземпляр сервиса
     */
    public static TextFileService getInstance() {
       return instance;
    }

    /**
     * Получить строковое представление частотности слов в текстовом файле
     * @param path путь к файлу
     * @return строковое представление частотности слов
     * @throws IOException исключение, выбрасываемое при необнаружении файла, лиюо при обнаружении файла неверного формата
     * @throws EmptyFileException исключение, выбрасываемое при пустом текстовом файле
     */
    public String getWordsFrequenciesInFile(String path) throws IOException, EmptyFileException {
        return getStringRepresentation(getWordsFrequencyMap(String.join(LINES_DELIMITER, readFileToStrings(path))));
    }

    private Map<String, Long> getWordsFrequencyMap(String string) {
        Map<String, Long> wordsFrequencies = new HashMap<>();

        String[] s = string.split(DELIMITERS_REGEX);

        Stream.of(string.split(DELIMITERS_REGEX))
                .collect(Collectors.groupingBy(w -> w, () -> wordsFrequencies, Collectors.counting()));

        return wordsFrequencies;
    }

    private String getStringRepresentation(Map<String, Long> wordsFrequencies) {
        return wordsFrequencies.entrySet().stream()
                .map(e -> e.getKey() + WORD_FREQ_DELIMITER + e.getValue()).collect(Collectors.joining("\n"));
    }

    private List<String> readFileToStrings(String path) throws IOException, EmptyFileException {
        List<String> lines = Files.readAllLines(Paths.get(path));

        if (lines.isEmpty()) {
            throw new EmptyFileException();
        }

        return lines;
    }
}
