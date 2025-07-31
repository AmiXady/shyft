package kz.amixady.shyft.modules.lineanalyzer.resolver;

import kz.amixady.shyft.shared.enums.LineType;

public interface SingleLineTypeResolver {
    boolean supports(String line);
    LineType type();
}
