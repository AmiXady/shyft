package kz.amixady;


import kz.amixady.cli.parser.ParsedArgs;
import kz.amixady.cli.factory.CliConfigFactory;
import kz.amixady.cli.factory.OutputConfigFactory;
import kz.amixady.cli.parser.ArgsParser;
import kz.amixady.sharp.WarningCollector;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        WarningCollector warningCollector =
                new WarningCollector();
        ArgsParser argsParser =
                new ArgsParser(warningCollector);

        OutputConfigFactory outputConfigFactory =
                new OutputConfigFactory();
        CliConfigFactory cliConfigFactory =
                new CliConfigFactory(outputConfigFactory);

        ParsedArgs parsedArgs = argsParser.parse(args);

        var config = cliConfigFactory.create(parsedArgs);

    }
}