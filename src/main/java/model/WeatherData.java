package model;

public record WeatherData(int dayNumber, double maxTemp, double minTemp) implements Comparable<WeatherData> {

    private double getTempDifference() {
        return Math.abs(maxTemp - minTemp);
    }


    @Override
    public int compareTo(WeatherData o) {
        return (int) (this.getTempDifference() - o.getTempDifference());
    }
}
