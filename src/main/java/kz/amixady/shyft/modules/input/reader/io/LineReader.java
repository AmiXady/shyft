package kz.amixady.shyft.modules.input.reader.io;



import kz.amixady.shyft.exeptions.FileReadException;

import java.io.IOException;


/**
 * Интерфейс для построчного чтения из одного источника.
 */
public interface LineReader  {
    /**
     * Читает следующую строку или возвращает null при достижении конца.
     * @return строка или null
     * @throws FileReadException при ошибке ввода-вывода
     */
    String readLine() throws FileReadException;

    /**
     * Проверяет, достигнут ли конец источника.
     * @return true, если источник исчерпан
     */
    boolean isFinished();


    void close() throws IOException;


}