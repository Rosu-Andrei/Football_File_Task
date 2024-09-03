package model;

/**
 * @param teamName     = the name of the team
 * @param goalsFor     = total number of goals that a team has scored thought a season
 * @param goalsAgainst = total number of goals a team has received thought a season
 *                     <p>
 *                     For code simplicity and immutability, the TeamData has been updated to a record. Also, it implements the Comparable
 *                     interface so that the business logic exists only in a single file.
 */
public record TeamData(String teamName, int goalsFor, int goalsAgainst) implements Comparable<TeamData> {

    private int getGoalDifference() {
        return Math.abs(goalsFor - goalsAgainst);
    }

    @Override
    public int compareTo(TeamData o) {
        return this.getGoalDifference() - o.getGoalDifference();
    }
}
