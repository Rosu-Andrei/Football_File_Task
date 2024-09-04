package generics;

import model.TeamData;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GenericMain {

    public static void main(String[] args) {
        Path filePath = Paths.get("./football.dat");
        GenericFileService<TeamData> service = new GenericFileService<>(filePath);
        List<TeamData> data = service.getData();
        System.out.println(data);
    }
}
