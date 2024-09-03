package model;

public record TeamDataV2(String teamName, int goalsFor, int goalsAgainst) implements Comparable<TeamDataV2> {

    private int getGoalDifference() {
        return Math.abs(goalsFor - goalsAgainst);
    }

    @Override
    public int compareTo(TeamDataV2 o) {
        return this.getGoalDifference() - o.getGoalDifference();
    }
}
