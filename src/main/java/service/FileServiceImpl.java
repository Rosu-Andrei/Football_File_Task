package service;

import model.TeamComparator;
import model.TeamData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileServiceImpl implements FileService {

    private final Pattern pattern;
    private final Path filePath;

    public FileServiceImpl(Pattern pattern, Path filePath) {
        this.pattern = pattern;
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
    @Override
    public List<TeamData> getTeamDataFromFile() {
        List<TeamData> teamDataList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line.trim());
                if (matcher.find()) {
                    String teamName = matcher.group(1).trim();
                    int goalsFor = Integer.parseInt(matcher.group(6));
                    int goalsAgainst = Integer.parseInt(matcher.group(7));
                    teamDataList.add(new TeamData(teamName, goalsFor, goalsAgainst));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return teamDataList;
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

        List<TeamData> teamDataList = getTeamDataFromFile();
        Optional<TeamData> result = teamDataList.stream()
                .min(new TeamComparator());
        if (result.isPresent())
            return result.get().getTeamName();
        else
            return "";
    }
}
