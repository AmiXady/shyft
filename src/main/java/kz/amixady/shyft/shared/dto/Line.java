package kz.amixady.shyft.shared.dto;


import kz.amixady.shyft.shared.enums.LineType;

public record Line(
        String line,
        LineType lineType
) {
}
