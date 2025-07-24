package kz.amixady.modules.input.reader;


import java.io.IOException;


/**
 * Интерфейс для построчного чтения из одного источника.
 */
public interface LineReader  {
    /**
     * Читает следующую строку или возвращает null при достижении конца.
     * @return строка или null
     * @throws IOException при ошибке ввода-вывода
     */
    String readLine() throws IOException;

    /**
     * Проверяет, достигнут ли конец источника.
     * @return true, если источник исчерпан
     */
    boolean isFinished();


    void close() throws IOException;


}