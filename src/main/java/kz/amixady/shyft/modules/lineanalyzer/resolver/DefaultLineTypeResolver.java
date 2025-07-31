package kz.amixady.shyft.modules.lineanalyzer.resolver;

import kz.amixady.shyft.shared.enums.LineType;

public class DefaultLineTypeResolver implements SingleLineTypeResolver {


    //по дефолту остальные являются строками , и дефолт должен применяться последним.
    @Override
    public boolean supports(String line) {
        return true;
    }

    @Override
    public LineType type() {
        return LineType.STRING;
    }
}
