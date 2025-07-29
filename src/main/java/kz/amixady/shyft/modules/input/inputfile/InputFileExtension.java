package kz.amixady.shyft.modules.input.inputfile;

//можно добавить любой другой тип для расшиение например md

import java.util.Arrays;

public enum InputFileExtension {
    TXT(".txt");

    private final String value;

    InputFileExtension(String value) {
        this.value = value;
    }

    public static boolean isSupported(String fileName) {
        return Arrays.stream(values())
                .anyMatch(ext -> fileName.endsWith(ext.value));
    }
}
