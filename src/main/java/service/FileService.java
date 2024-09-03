package service;

import model.TeamData;
import model.WeatherData;

import java.util.stream.Stream;


public interface FileService {

    Stream<TeamData> getTeamDataFromFile();

    Stream<WeatherData> getWeatherDataFromFile();

    String getTeamNameWithLeastDifference();

    int getDayWithLeastTempDifference();
}
