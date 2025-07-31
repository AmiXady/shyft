package kz.amixady.shyft;
import kz.amixady.shyft.modules.cli.resolver.CliConfigResolverImpl;
import kz.amixady.shyft.modules.input.factory.BatchReaderFactory;
import kz.amixady.shyft.shared.WarningCollector;
import kz.amixady.shyft.shared.dto.CliConfig;
import kz.amixady.shyft.shared.dto.Line;
import kz.amixady.shyft.shared.interfaces.PartReader;
import kz.amixady.shyft.shared.interfaces.CliConfigResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ShyftApplication {
    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(ShyftApplication.class, args);

        CliConfigResolver cliConfigResolver =
                context.getBean(CliConfigResolverImpl.class);

        String args1 [] = {"data.txt","-a","-o","sex","-p","-s","s","-f"};

        CliConfig cliConfig = cliConfigResolver.resolve(args1);

        BatchReaderFactory batchReaderFactory =
                context.getBean(BatchReaderFactory.class);

        PartReader partReader =
                batchReaderFactory.create(cliConfig.inputFiles());

        while(true) {
            var batch = partReader.readNextBatch();
            if(batch.isEmpty()){
                break;
            }
            for(Line line : batch){
                System.out.print(line.line()+"|");
            }
            System.out.println();
        }
        var collector = context.getBean(WarningCollector.class);
        collector.printAll();

    }
}
