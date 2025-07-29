package kz.amixady.shyft.shared.interfaces;

import kz.amixady.shyft.shared.dto.CliConfig;

public interface CliConfigResolver {
    CliConfig resolve(String[] args);
}
