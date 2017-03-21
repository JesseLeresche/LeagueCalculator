package service;

import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MatchServiceTest {

    private static final Integer WIN_POINTS = 3;
    private static final Integer LOSE_POINTS = 0;
    private static final Integer DRAW_POINTS = 1;
    private static final String HOME_TEAM = "Lions";
    private static final Integer HOME_SCORE = 3;
    private static final String AWAY_TEAM = "Snakes";
    private static final String HOME_GAME_ENTRY = HOME_TEAM + " " + HOME_SCORE;

    private MatchService matchService;

    private Team homeTeam;
    private Team awayTeam;

    @BeforeEach
    public void setUp() {
        matchService = new MatchService();
        homeTeam = new Team(HOME_TEAM);
        awayTeam = new Team(AWAY_TEAM);
    }

    @Test
    public void testCalculatePointsForMatchHomeTeamWins() {
        Integer homeTeamScore = 5;
        Integer awayTeamScore = 1;

        matchService.calculatePointsForMatch(homeTeam, homeTeamScore, awayTeam, awayTeamScore);

        assertEquals(WIN_POINTS, homeTeam.getPoints(), "Home team won and should have 3 points");
        assertEquals(LOSE_POINTS, awayTeam.getPoints(), "Away team lost and should have 0 points");
    }

    @Test
    void testCalculatePointsForMatchAwayTeamWins() {
        Integer homeTeamScore = 1;
        Integer awayTeamScore = 5;

        matchService.calculatePointsForMatch(homeTeam, homeTeamScore, awayTeam, awayTeamScore);

        assertEquals(WIN_POINTS, awayTeam.getPoints(), "Away team won and should have " + WIN_POINTS + " points");
        assertEquals(LOSE_POINTS, homeTeam.getPoints(), "Home team lost and should have " + LOSE_POINTS + " points");
    }

    @Test
    public void testCalculatePointsForMatchDraw() {
        Integer homeTeamScore = 1;
        Integer awayTeamScore = 1;

        matchService.calculatePointsForMatch(homeTeam, homeTeamScore, awayTeam, awayTeamScore);

        assertEquals(DRAW_POINTS, homeTeam.getPoints(), "Home team drew and should have " + DRAW_POINTS + " point");
    }

    @Test
    public void testResolveTeamWithEmptyMap() {
        Team resolvedTeam = matchService.resolveTeam(HOME_GAME_ENTRY);
        Map<String, Team> teamMap = matchService.getTeamMap();

        assertNotNull(teamMap, "TeamMap should not be NULL");
        assertNotNull(resolvedTeam, "model.Team should be populated");
        assertEquals(1, teamMap.size(), "TeamMap should contain exactly 1 element");
        assertEquals(teamMap.get(resolvedTeam.getTeamName()), resolvedTeam, "The resolvedTeam should be the same object as the element in the teamMNap");
        assertEquals(HOME_TEAM, resolvedTeam.getTeamName(), "The resolvedTeam team name should be " + HOME_TEAM);
    }

    @Test
    public void testResolveTeamWithPopulatedMap() {
        Map<String, Team> populatedTeamMap = new HashMap<>();
        String aTeam = "A model.Team";
        String anotherTeam = "Another model.Team";
        populatedTeamMap.put(aTeam, new Team(aTeam));
        populatedTeamMap.put(anotherTeam, new Team(anotherTeam));
        matchService.setTeamMap(populatedTeamMap);

        Team resolvedTeam = matchService.resolveTeam(HOME_GAME_ENTRY);
        Map<String, Team> retrievedTeamMap = matchService.getTeamMap();

        assertNotNull(retrievedTeamMap, "TeamMap should not be NULL");
        assertNotNull(resolvedTeam, "model.Team should be populated");
        assertEquals(3, retrievedTeamMap.size(), "TeamMap should contain exactly 1 element");
        assertEquals(retrievedTeamMap.get(resolvedTeam.getTeamName()), resolvedTeam, "The resolvedTeam should be the same object as the element in the teamMNap");
        assertEquals(HOME_TEAM, resolvedTeam.getTeamName(), "The resolvedTeam team name should be " + HOME_TEAM);
    }

    @Test
    public void testResolveTeamAlreadyInMap() {
        Map<String, Team> populatedTeamMap = new HashMap<>();
        populatedTeamMap.put(HOME_TEAM, new Team(HOME_TEAM));
        String anotherTeam = "Another model.Team";
        populatedTeamMap.put(anotherTeam, new Team(anotherTeam));
        matchService.setTeamMap(populatedTeamMap);

        Team resolvedTeam = matchService.resolveTeam(HOME_GAME_ENTRY);
        Map<String, Team> retrievedTeamMap = matchService.getTeamMap();

        assertNotNull(retrievedTeamMap, "TeamMap should not be NULL");
        assertNotNull(resolvedTeam, "model.Team should be populated");
        assertEquals(2, retrievedTeamMap.size(), "TeamMap should contain exactly 1 element");
        assertEquals(retrievedTeamMap.get(resolvedTeam.getTeamName()), resolvedTeam, "The resolvedTeam should be the same object as the element in the teamMNap");
        assertEquals(HOME_TEAM, resolvedTeam.getTeamName(), "The resolvedTeam team name should be " + HOME_TEAM);
    }

    @Test
    public void testResolveScore() {
        Integer resolvedScore = matchService.resolveScore(HOME_GAME_ENTRY);

        assertNotNull(resolvedScore, "Score should not be null");
        assertEquals(HOME_SCORE, resolvedScore, "The score should be equal to " + HOME_SCORE);
    }
}
