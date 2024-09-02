package model;
import java.util.Comparator;

/**
 * The following class is a Comparator that is used for the reduction operation in the stream of TeamData.
 * It compares the goal difference between two teams to extract the team that has the smallest difference.
 */
public class TeamComparator implements Comparator<TeamData> {

    @Override
    public int compare(TeamData teamData1, TeamData teamData2) {
        return teamData1.goalDifference() - teamData2.goalDifference();
    }
}
