import model.Team;
import service.FileService;

import java.util.Map;

public class LeagueCalculator {

    public static void main(String[] args) {
        FileService fileService = new FileService();
        Map<String, Team> teamMap = fileService.readMatchesFromFile(args[0]);
        fileService.writeRankingsToFile(args[1], teamMap);
    }

}