package kz.amixady.shyft.modules.lineanalyzer.resolver;

import kz.amixady.shyft.shared.enums.LineType;

import java.math.BigDecimal;

public class FloatLineTypeResolver implements SingleLineTypeResolver{

    @Override
    public boolean supports(String line) {
        try {
            BigDecimal decimal = new BigDecimal(line.trim());
            // Проверяем, содержит ли точку или экспоненту, чтобы не путать с целым
            return line.contains(".") || line.toLowerCase().contains("e");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public LineType type() {
        return LineType.FLOAT;
    }
}
