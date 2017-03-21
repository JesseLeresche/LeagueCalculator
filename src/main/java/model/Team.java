package model;

/**
 * This is the domain model for the Team object.
 */
public class Team {

    private String teamName;

    private Integer points;

    public Team(String teamName) {
        this.teamName = teamName;
        this.points = 0;
    }

    public Team(String teamName, Integer points) {
        this.teamName = teamName;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public Integer getPoints() {
        return points;
    }

    public void addPoints(Integer numberOfPoints) {
        this.points += numberOfPoints;
    }

    @Override
    public String toString() {
        return teamName + ", " + points + (points != 1 ? " pts" : " pt");
    }
}
