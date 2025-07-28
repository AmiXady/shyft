package kz.amixady.shyft.modules.cli.resolver;

import kz.amixady.shyft.modules.cli.enums.Flag;
import kz.amixady.shyft.shared.constants.DefaultArgValues;
import kz.amixady.shyft.shared.enums.StatsMode;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class StatsModeResolver {
    public  StatsMode resolve(Set<Flag> flags) {
        if (flags.contains(Flag.FULL_STATS)) {
            return StatsMode.FULL;
        } else if (flags.contains(Flag.SHORT_STATS)) {
            return StatsMode.SHORT;
        } else {
            return DefaultArgValues.DEFAULT_STATS_MODE;
        }
    }
}
