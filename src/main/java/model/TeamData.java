package model;

/**
 * The following class is used to encapsulate the data that we want to extract from the file:
 * 1. the team name.
 * 2. the total number of goals that the team scored.
 * 3. the total number of goals that the team received.
 */
public class TeamData {
    private String teamName;
    private int goalsFor;
    private int goalsAgainst;

    public TeamData(String teamName, int goalsFor, int goalsAgainst) {
        this.teamName = teamName;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public TeamData() {

    }

    @Override
    public String toString() {
        return "TeamData{" +
                "teamName='" + teamName + '\'' +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                '}';
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * @return as an integer the difference between goals scored and goals received by a team
     */
    public int goalDifference() {
        return Math.abs(this.goalsFor - this.goalsAgainst);
    }
}
