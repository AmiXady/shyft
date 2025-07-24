package kz.amixady.cli.parser;

import kz.amixady.cli.enums.Flag;
import kz.amixady.cli.enums.Option;
import kz.amixady.sharp.WarninMessage;
import kz.amixady.sharp.WarningCollector;

import java.util.*;

public class ArgsParser {
    private final WarningCollector warningCollector;

    public ArgsParser(WarningCollector warningCollector) {
        this.warningCollector = warningCollector;
    }

    public ParsedArgs parse(String [] args ){
        Map<Option,String> options =
                new HashMap<>();
        Set<Flag> flags =
                new HashSet<>();
        List<String> files =
                new ArrayList<>();

        for(int i=0;i< args.length;i++) {
            var option = Option.fromName(args[i]);
            if (option.isPresent()) {
                if (i + 1 < args.length) {
                    options.put(option.get(), args[++i]);
                } else if(!options.containsKey(option.get())){
                    //иначе если опция не добавлена ничего не добавляем и будем использова опцию по умолчанию
                    warningCollector.add(
                            String.format(WarninMessage.OPTION_WITHOUT_VALUE, args[i]));
                }
                continue;
            }

            var flag = Flag.fromName(args[i]);
            if (flag.isPresent()) {
                flags.add(flag.get());
            } else {
                //если аргумент не является опцией/флагом то читаем его за файла
                files.add(args[i]);
            }
        }
        return new ParsedArgs(options,flags,files);
    }
}
