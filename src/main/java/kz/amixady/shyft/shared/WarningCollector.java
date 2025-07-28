package kz.amixady.shyft.shared;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class WarningCollector {

    private final List<String> warnings = new ArrayList<>();

    public void add(String message) {
        if (message != null && !message.isBlank()) {
            warnings.add(message);
        }
    }

    public boolean hasWarnings() {
        return !warnings.isEmpty();
    }

    public List<String> getWarnings() {
        return Collections.unmodifiableList(warnings);
    }

    public void printAll() {
        if (!warnings.isEmpty()) {
            System.out.println("⚠️ Предупреждения:");
            for (String warning : warnings) {
                System.out.println(" - " + warning);
            }
        }
    }

    public void clear() {
        warnings.clear();
    }
}
