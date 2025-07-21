package kz.amixady;

import kz.amixady.input.BatchReader;
import kz.amixady.input.factory.BatchReaderFactory;
import kz.amixady.sharp.WarningCollector;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        WarningCollector warningCollector =
                new WarningCollector();

        String files [] = {"d1.txt","sex/d1.txt","d3.txt"};

        BatchReaderFactory batchReaderFactory =
                new BatchReaderFactory(warningCollector);

        BatchReader batchReader = batchReaderFactory.create(files);

        while(true) {
            var lines = batchReader.readNextBatch();
            if(lines.isEmpty()) break;
            for(String line : lines) {
                System.out.print(line+" ");
            }
            System.out.print("END\n");
        }

        warningCollector.printAll();
    }
}