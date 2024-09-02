package service;

import model.TeamComparator;
import model.TeamStats;

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

    @Override
    public List<TeamStats> getTeamDataFromFile() {
        List<TeamStats> teamStatsList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line.trim());
                if (matcher.find()) {
                    String teamName = matcher.group(1).trim();
                    int goalsFor = Integer.parseInt(matcher.group(6));
                    int goalsAgainst = Integer.parseInt(matcher.group(7));
                    teamStatsList.add(new TeamStats(teamName, goalsFor, goalsAgainst));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return teamStatsList;
    }

    @Override
    public String getTeamNameWithLeastDifference() {

        List<TeamStats> teamStatsList = getTeamDataFromFile();
        Optional<TeamStats> result = teamStatsList.stream()
                .min(new TeamComparator());
        if (result.isPresent())
            return result.get().getTeamName();
        else
            return "";
    }
}
