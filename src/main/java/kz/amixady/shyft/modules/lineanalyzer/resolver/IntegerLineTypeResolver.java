package kz.amixady.shyft.modules.lineanalyzer.resolver;

import kz.amixady.shyft.shared.enums.LineType;

import java.math.BigInteger;

public class IntegerLineTypeResolver implements SingleLineTypeResolver {

    @Override
    public boolean supports(String line) {
        try {
            new BigInteger(line.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public LineType type() {
        return LineType.INTEGER;
    }
}
