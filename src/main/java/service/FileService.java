package service;

import model.TeamDataV2;

import java.util.stream.Stream;

public interface FileService {
    /**
     * Try to modify List with Stream
     *
     * @return
     */
    Stream<TeamDataV2> getTeamDataFromFile();

    String getTeamNameWithLeastDifference();
}
