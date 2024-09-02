import service.FileService;
import service.FileServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("^\\d+\\.\\s+(.+?)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+-\\s+(\\d+)");
        Path filePath = Paths.get("./football.dat");


        FileService fileService = new FileServiceImpl(pattern, filePath);
        String teamName = fileService.getTeamNameWithLeastDifference();
        System.out.println("Name of the team is: " + teamName);

    }
}
