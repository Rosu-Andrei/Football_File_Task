package service;

import model.TeamStats;

import java.util.List;

public interface FileService {

    List<TeamStats> getTeamDataFromFile();

    String getTeamNameWithLeastDifference();
}
