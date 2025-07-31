package kz.amixady.shyft.shared.dto;

import kz.amixady.shyft.shared.enums.LineType;

import java.util.*;

public class StructuredLines {

    private final Map<LineType, List<String>> groupedLines = new HashMap<>();

    // Добавить одну строку к указанному типу
    public void addLine(LineType type, String line) {
        groupedLines.computeIfAbsent(type, k -> new ArrayList<>()).add(line);
    }

    // Получить все строки указанного типа
    public List<String> getByType(LineType type) {
        return groupedLines.getOrDefault(type, List.of());
    }


    // Проверить, содержит ли строки определённого типа
    public boolean hasType(LineType type) {
        return groupedLines.containsKey(type);
    }
}
