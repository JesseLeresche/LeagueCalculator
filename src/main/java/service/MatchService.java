package service;

import model.Team;

import java.util.HashMap;
import java.util.Map;

/**
 * The MatchService takes the rows from a file and converts it into a match, as well as calculating the results of the matches
 */
public class MatchService {

    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private static final Integer WIN_POINTS = 3;
    private static final Integer DRAW_POINTS = 1;

    private Map<String, Team> teamMap = new HashMap<>();

    public void mapFileEntryForMatch(String fileRow) {
        String[] split = fileRow.split(COMMA);
        String homeTeamString = split[0].trim();
        String awayTeamString = split[1].trim();
        Team homeTeam = resolveTeam(homeTeamString);
        Team awayTeam = resolveTeam(awayTeamString);
        Integer homeTeamScore = resolveScore(homeTeamString);
        Integer awayTeamScore = resolveScore(awayTeamString);
        calculatePointsForMatch(homeTeam, homeTeamScore, awayTeam, awayTeamScore);
    }

    protected void calculatePointsForMatch(Team homeTeam, Integer homeTeamScore, Team awayTeam, Integer awayTeamScore) {
        Integer gameResult = Integer.signum(homeTeamScore - awayTeamScore);
        switch (gameResult) {
            case -1:
                awayTeam.addPoints(WIN_POINTS);
                break;
            case 0:
                awayTeam.addPoints(DRAW_POINTS);
                homeTeam.addPoints(DRAW_POINTS);
                break;
            case 1:
                homeTeam.addPoints(WIN_POINTS);
                break;
        }
    }

    protected Team resolveTeam(String gameEntry) {
        String teamName = gameEntry.substring(0, gameEntry.lastIndexOf(SPACE));
        Team foundTeam = teamMap.get(teamName);
        if (teamMap.size() == 0 || foundTeam == null) {
            foundTeam = new Team(teamName);
            teamMap.put(teamName, foundTeam);
        }
        return foundTeam;
    }

    protected Integer resolveScore(String gameEntry) {
        return Integer.valueOf(gameEntry.substring(gameEntry.lastIndexOf(SPACE) + 1));
    }

    protected Map<String, Team> getTeamMap() {
        return teamMap;
    }

    protected void setTeamMap(Map<String, Team> teamMap) {
        this.teamMap = teamMap;
    }
}
