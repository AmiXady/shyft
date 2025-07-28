package kz.amixady.shyft.shared.dto;


import kz.amixady.shyft.shared.enums.StatsMode;

import java.util.List;

public record CliConfig(
        List<String> inputFiles,
        OutputConfig outputConfig,
        StatsMode statsMode
){

    @Override
    public String toString() {
        return "CliConfig\n" +
                "inputFiles=" + inputFiles +
                "\noutputConfig=" + outputConfig +
                "\nstatsMode=" + statsMode;
    }
}
