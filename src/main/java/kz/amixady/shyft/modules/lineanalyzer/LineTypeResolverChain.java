package kz.amixady.shyft.modules.lineanalyzer;


import kz.amixady.shyft.modules.lineanalyzer.resolver.DefaultLineTypeResolver;
import kz.amixady.shyft.modules.lineanalyzer.resolver.FloatLineTypeResolver;
import kz.amixady.shyft.modules.lineanalyzer.resolver.IntegerLineTypeResolver;
import kz.amixady.shyft.modules.lineanalyzer.resolver.SingleLineTypeResolver;
import kz.amixady.shyft.shared.enums.LineType;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LineTypeResolverChain {

    private final List<SingleLineTypeResolver> resolvers;
    private final SingleLineTypeResolver defaultLineTypeResolver;


    //решил так передавать так как
    //список фиксирован и не зависит от внешних условий
    public LineTypeResolverChain() {
        this.resolvers = List.of(
                new IntegerLineTypeResolver(),
                new FloatLineTypeResolver()
        );
        defaultLineTypeResolver =
                new DefaultLineTypeResolver();
    }

    public LineType resolveType(String line) {
        for (SingleLineTypeResolver resolver : resolvers) {
            if (resolver.supports(line)) {
                return resolver.type();
            }
        }
        return defaultLineTypeResolver.type();
    }
}