package service;

import model.TeamDataV2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileServiceImplTest {

    private FileService fileService;
    private Pattern mockPattern;
    private Path mockPath;
    private static MockedStatic<Files> mockedSettings;

    @BeforeEach
    void setup() {
        mockPath = mock(Path.class);
        mockPattern = Pattern.compile("^\\d+\\.\\s+(.+?)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+-\\s+(\\d+)");
        fileService = new FileServiceImpl(mockPath);
    }

    @BeforeAll
    static void init() {
        mockedSettings = mockStatic(Files.class);
    }

    @AfterAll
    static void close() {
        mockedSettings.close();
    }

    @Test
    void sizeOfDataFromFilesIsTwo() throws IOException {
        List<String> mockFileContent = List.of(
                "  1. Arsenal         38    26   9   3    79  -  36    87",
                "  2. Liverpool       38    24   8   6    67  -  30    80",
                "  3. Manchester_U    38    24   5   9    87  -  45    77"
        );
        when(Files.readAllLines(mockPath)).thenReturn(mockFileContent);
        Stream<TeamDataV2> teamDataFromFile = fileService.getTeamDataFromFile();
        assertEquals(3, teamDataFromFile.count());
    }

    @Test
    void nameOfTheSecondTeamExtractedIsLiverpool() throws IOException {
        List<String> mockFileContent = List.of(
                "  1. Arsenal         38    26   9   3    79  -  36    87",
                "  2. Liverpool       38    24   8   6    67  -  30    80",
                "  3. Manchester_U    38    24   5   9    87  -  45    77"
        );
        when(Files.readAllLines(mockPath)).thenReturn(mockFileContent);
        List<TeamDataV2> teamDataFromStream = fileService.getTeamDataFromFile().toList();
        assertEquals("Liverpool", teamDataFromStream.get(1).teamName());
    }

    @Test
    void numberOfGoalsScoredForArsenalAreSeventyNine() throws IOException {
        List<String> mockFileContent = List.of(
                "  1. Arsenal         38    26   9   3    79  -  36    87",
                "  2. Liverpool       38    24   8   6    67  -  30    80",
                "  3. Manchester_U    38    24   5   9    87  -  45    77"
        );
        when(Files.readAllLines(mockPath)).thenReturn(mockFileContent);
        List<TeamDataV2> teamDataFromFile = fileService.getTeamDataFromFile().toList();
        assertEquals(79, teamDataFromFile.get(0).goalsFor());
    }

    @Test
    void getDataFromEmptyFile() throws IOException {
        when(Files.readAllLines(mockPath)).thenReturn(List.of());
        Stream<TeamDataV2> teamDataFromFile = fileService.getTeamDataFromFile();
        assertEquals(0, teamDataFromFile.count());
    }

    @Test
    void getDataFromFileThatHasJunkData() throws IOException {

        List<String> mockFileContent = List.of(
                " Team            P     W    L   D    F      A     Pts",
                "    1. Arsenal         38    26   9   3    79  -  36    87",
                "    2. Liverpool       38    24   8   6    67  -  30    80",
                "    3. Manchester_U    38    24   5   9    87  -  45    77",
                "    4. Newcastle       38    21   8   9    74  -  52    71",
                "    5. Leeds           38    18  12   8    53  -  37    66",
                "    6. Chelsea         38    17  13   8    66  -  38    64",
                "    7. West_Ham        38    15   8  15    48  -  57    53",
                "    ######################################################"
        );
        when(Files.readAllLines(mockPath)).thenReturn(mockFileContent);
        Stream<TeamDataV2> teamDataFromFile = fileService.getTeamDataFromFile();
        assertEquals(7, teamDataFromFile.count());
    }

    @Test
    void testRuntimeExceptionIsThrown() throws Exception {
        when(Files.readAllLines(mockPath)).thenThrow(new IOException("File not found"));
        assertThrows(RuntimeException.class, () -> fileService.getTeamDataFromFile());
    }

    @Test
    void teamWithLeastDifferenceIsWestHam() throws IOException {
        List<String> mockFileContent = List.of(
                " Team            P     W    L   D    F      A     Pts",
                "    1. Arsenal         38    26   9   3    79  -  36    87",
                "    2. Liverpool       38    24   8   6    67  -  30    80",
                "    3. Manchester_U    38    24   5   9    87  -  45    77",
                "    4. Newcastle       38    21   8   9    74  -  52    71",
                "    5. Leeds           38    18  12   8    53  -  37    66",
                "    6. Chelsea         38    17  13   8    66  -  38    64",
                "    7. West_Ham        38    15   8  15    48  -  57    53",
                "    ######################################################"
        );
        when(Files.readAllLines(mockPath)).thenReturn(mockFileContent);
        String teamName = fileService.getTeamNameWithLeastDifference();
        assertEquals("West_Ham", teamName);
    }

    @Test
    void testPatternMatching() {
        String lineFromFile = "    1. Arsenal         38    26   9   3    79  -  36    87";
        Matcher matcher = mockPattern.matcher(lineFromFile.trim());
        assertTrue(matcher.find());
    }

    @Test
    void testThatNameCapturedWasArsenal() {
        String lineFromFile = "    1. Arsenal         38    26   9   3    79  -  36    87";
        Matcher matcher = mockPattern.matcher(lineFromFile.trim());
        String teamName = "";
        if (matcher.find()) {
            teamName = matcher.group(1).trim();
        }
        assertEquals("Arsenal", teamName);
    }

    @Test
    void secondTeamGoalsForIsSeventyNine() throws IOException {
        String lineFromFile = "    1. Arsenal         38    26   9   3    79  -  36    87";
        Matcher matcher = mockPattern.matcher(lineFromFile.trim());
        int goalsFor = 0;
        if (matcher.find()) {
            goalsFor = Integer.parseInt(matcher.group(6));
        }
        assertEquals(79, goalsFor);
    }

}