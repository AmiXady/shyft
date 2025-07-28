package kz.amixady.shyft.shared.constants;

public class ValidationWarningMessage {


    //input
    private static final String IGNORED_SUFFIX = "Данный файл в дальнейшем будет проигнорирован.";

    public static final String FILE_NOT_FOUND = "\nФайл не существует: %s."+IGNORED_SUFFIX;
    public static final String NOT_REGULAR_FILE = "\nНе является обычным файлом: %s."+IGNORED_SUFFIX;
    public static final String FILE_NOT_READABLE = "\nНет доступа к файлу: %s."+IGNORED_SUFFIX;
    public static final String UNSUPPORTED_EXTENSION = "\nНеподдерживаемое расширение у файла %s."+IGNORED_SUFFIX;


    //output prefix
    public static final String TOO_LONG_PREFIX = "Префикс слишком длинный: максимум %d символов. Остальная часть префикса будет проигнорирована.";
    public static final String PREFIX_ERROR_ILLEGAL_CHARACTERS =
            "Префикс содержит недопустимые символы. Разрешены только латинские буквы," +
                    " цифры, подчёркивания (_), точки (.) и тире (-). Префикс будет отсутствовать.";

    //output dir



}
