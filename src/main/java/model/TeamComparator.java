package model;
import java.util.Comparator;

public class TeamComparator implements Comparator<TeamStats> {

    @Override
    public int compare(TeamStats teamStats1, TeamStats teamStats2) {
        return teamStats1.goalDifference() - teamStats2.goalDifference();
    }
}
