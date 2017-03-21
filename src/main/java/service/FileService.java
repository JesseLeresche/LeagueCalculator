package service;

import model.Team;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class handles all of the file inputs and outputs, such as the input file and writing the output file
 */
public class FileService {

    public Map<String, Team> readMatchesFromFile(String inputFilePath) {
        Map<String, Team> teamMap;
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath))) {
            MatchService matchService = new MatchService();
            stream.forEach(matchService::mapFileEntryForMatch);
            teamMap = matchService.getTeamMap();
        } catch (IOException e) {
            e.printStackTrace();
            teamMap = new HashMap<>();
        }
        return teamMap;
    }

    public void writeRankingsToFile(String outputFilePath, Map<String, Team> teamMap) {
        try {
            final Integer[] counter = {1};
            Files.write(Paths.get(outputFilePath), (Iterable<String>) teamMap.entrySet().stream().sorted((firstElement, secondElement) -> {
                if (firstElement.getValue().getPoints().equals(secondElement.getValue().getPoints())) {
                    return firstElement.getValue().getTeamName().compareTo(secondElement.getValue().getTeamName());
                } else {
                    return firstElement.getValue().getPoints() > secondElement.getValue().getPoints() ? -1 : 1;
                }
            }).map(i -> mapRankingToFileEntry(i.getValue(), counter[0]++))::iterator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String mapRankingToFileEntry(Team team, Integer counter) {
        return counter + ". " + team.toString();
    }
}
