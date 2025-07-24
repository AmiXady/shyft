package kz.amixady.cli.config;

import kz.amixady.cli.enums.StatsMode;

import java.util.List;

public record CliConfig(
        List<String> inputFiles,
        OutputConfig outputConfig,
        StatsMode statsMode
){

}
