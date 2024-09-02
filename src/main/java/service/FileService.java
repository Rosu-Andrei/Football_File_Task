package service;

import model.TeamData;

import java.util.List;

public interface FileService {

    List<TeamData> getTeamDataFromFile();

    String getTeamNameWithLeastDifference();
}
