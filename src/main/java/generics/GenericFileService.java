package generics;

import model.TeamData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class GenericFileService<T extends TeamData> {

    private final Pattern pattern = Pattern.compile("^\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)|^\\d+\\.\\s+(.+?)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+-\\s+(\\d+)");
    private final Path filePath;

    public GenericFileService(Path filePath) {
        this.filePath = filePath;
    }

    List<T> getData() {
        List<T> dataList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line.trim());
                if (matcher.find()) {
                    String teamName = matcher.group(4).trim();
                    int goalsFor = Integer.parseInt(matcher.group(9));
                    int goalsAgainst = Integer.parseInt(matcher.group(10));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (List<T>) dataList;
    }
}
