package service;

import model.Team;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileServiceTest {

    private FileService fileService;

    @BeforeEach
    public void setUp() {
        fileService = new FileService();
    }

    @Test
    public void testReadMatchesFromFile() throws URISyntaxException {
        String inputFilePath = getClass().getClassLoader().getResource("input.txt").getPath();

        Map<String, Team> teamMap = fileService.readMatchesFromFile(inputFilePath);

        assertNotNull(teamMap, "THe teamMap should not be NULL");
        assertEquals(5, teamMap.size(), "The teamMap should contain exactly 5 elements");
    }

    @Test
    public void testWriteRankingsToFile() throws IOException {
        Map<String, Team> teamMap = new HashMap<>();
        teamMap.put("Snakes", new Team("Snakes", 1));
        teamMap.put("Lions", new Team("Lions", 5));
        teamMap.put("FC Awesome", new Team("FC Awesome", 1));
        teamMap.put("Grouches", new Team("Grouches", 0));
        teamMap.put("Tarantulas", new Team("Tarantulas", 6));

        String outputFilePath = getClass().getClassLoader().getResource("testOutput.txt").getPath();
        String expectedOutputFilePath = getClass().getClassLoader().getResource("output.txt").getPath();

        fileService.writeRankingsToFile(outputFilePath, teamMap);

        assertTrue(FileUtils.contentEquals(new File(outputFilePath), new File(expectedOutputFilePath)));
    }
}
