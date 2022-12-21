import services.IOService;

/**
 * Главный класс для запуска приложения
 */
public class Application {
    private static final String GREETING = "Приложение запущено\n";

    /**
     * Запуск приложения
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        System.out.println(GREETING);

        IOService.getInstance().handleInputs();
    }
}
