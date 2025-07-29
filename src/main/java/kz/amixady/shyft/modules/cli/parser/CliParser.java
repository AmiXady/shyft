package kz.amixady.shyft.modules.cli.parser;



import kz.amixady.shyft.modules.cli.enums.Flag;
import kz.amixady.shyft.modules.cli.enums.Option;
import kz.amixady.shyft.shared.WarningCollector;
import kz.amixady.shyft.shared.constants.WarningMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CliParser implements Parser {

    private final WarningCollector warningCollector;

    @Autowired
    public CliParser(WarningCollector warningCollector) {
        this.warningCollector = warningCollector;
    }

    @Override
    public Args parse(String [] args ){
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
                            String.format(WarningMessage.OPTION_WITHOUT_VALUE, args[i]));
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
        return new Args(options,flags,files);
    }
}
