import service.FileService;
import service.FileServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {


        Path filePath = Paths.get("./football.dat");


        FileService fileService = new FileServiceImpl(filePath);
        String teamName = fileService.getTeamNameWithLeastDifference();
        System.out.println("Name of the team is: " + teamName);

    }
}
