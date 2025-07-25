package kz.amixady.modules.line;


import java.math.BigDecimal;
import java.math.BigInteger;

public class LineTypeResolverImpl implements LineTypeResolver {

    @Override
    public Line resolve(String line) {
        String trimmed = line.trim();
        LineType lineType = LineType.STRING;

        if (isInteger(trimmed)) {
            lineType = LineType.INTEGER;
        } else if (isFloat(trimmed)) {
            lineType = LineType.FLOAT;
        }

        return new Line(line, lineType);
    }

    private boolean isInteger(String s) {
        try {
            new BigInteger(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String s) {
        try {
            new BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}