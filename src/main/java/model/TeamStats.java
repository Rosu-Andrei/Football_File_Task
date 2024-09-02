package model;

public class TeamStats {
    private String teamName;
    private int goalsFor;
    private int goalsAgainst;

    public TeamStats(String teamName, int goalsFor, int goalsAgainst) {
        this.teamName = teamName;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public TeamStats(){

    }

    @Override
    public String toString() {
        return "model.TeamStats{" +
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

    public int goalDifference() {
        return Math.abs(this.goalsFor - this.goalsAgainst);
    }
}
