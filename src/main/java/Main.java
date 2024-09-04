import service.FileService;
import service.FileServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {


        Path footballPath = Paths.get("./football.dat");
        Path weatherPath = Paths.get("./weather.txt");


        FileService fileService = new FileServiceImpl(footballPath);
        String teamName = fileService.getTeamNameWithLeastDifference();
        System.out.println("Name of the team is: " + teamName);
        /*int dayNumber = fileService.getDayWithLeastTempDifference();
        System.out.println("The day with the least temp difference is " + dayNumber);*/

    }
}
