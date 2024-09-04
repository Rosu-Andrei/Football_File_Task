package service;

import model.TeamData;
import model.WeatherData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileServiceImpl implements FileService {

    private final Pattern pattern = Pattern.compile("^\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)|^\\d+\\.\\s+(.+?)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+-\\s+(\\d+)");
    private final Path filePath;

    public FileServiceImpl(Path filePath) {
        this.filePath = filePath;
    }


    /**
     * The following method is used to extract the important data from the input file, in this case the team name,
     * the team's total number of goals scored and the team's total number of goals received.
     * To facilitate the extraction in a safe manner, the method uses a Regex Pattern so that headers and
     * unhealthy data are not used.
     *
     * @return a list of TeamData, each one representing the information of a single team
     */

    // Probabil aici o sa mai avem o metoda la fel doar ca aceea se va numi getWeatherDataFromFile.
    @Override
    public Stream<TeamData> getTeamDataFromFile() {
        Stream<TeamData> v2Stram = Stream.<TeamData>builder().build();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line.trim());
                System.out.println("Matcher count for football: " + matcher.groupCount());
                if (matcher.find()) {
                    String teamName = matcher.group(4).trim();
                    int goalsFor = Integer.parseInt(matcher.group(9));
                    int goalsAgainst = Integer.parseInt(matcher.group(10));
                    v2Stram = Stream.concat(v2Stram, Stream.of(new TeamData(teamName, goalsFor, goalsAgainst)));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return v2Stram;
    }

    /**
     * This method is used to retrieve the name of the team that has the smallest goal difference. It does this by
     * converting the list of all TeamData into a stream, and then it applies a reduction to that stream,
     * more precisely the min() operation.
     *
     * @return the name of the team that has the smallest difference between goals scored and goals received.
     */
    @Override
    public String getTeamNameWithLeastDifference() {

        Optional<TeamData> result = getTeamDataFromFile().
                min(TeamData::compareTo);
        if (result.isPresent())
            return result.get().teamName();
        else
            return "";
    }

    @Override
    public Stream<WeatherData> getWeatherDataFromFile() {
        Stream<WeatherData> stream = Stream.<WeatherData>builder().build();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line.trim());
                System.out.println("Matcher count for weather: " + matcher.groupCount());
                if (matcher.find()) {
                    int dayNumber = Integer.parseInt(matcher.group(1));
                    int maxTemp = Integer.parseInt(matcher.group(2));
                    int minTemp = Integer.parseInt(matcher.group(3));
                    stream = Stream.concat(stream, Stream.of(new WeatherData(dayNumber, maxTemp, minTemp)));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stream;
    }

    @Override
    public int getDayWithLeastTempDifference() {
        Optional<WeatherData> result = getWeatherDataFromFile().
                min(WeatherData::compareTo);
        if (result.isPresent())
            return result.get().dayNumber();
        else
            return 0;
    }
}
