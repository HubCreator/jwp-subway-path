package subway.domain.station;

import java.util.Objects;

public class Station {

    private Long id;
    private StationName stationName;

    private Station() {
    }

    public Station(final String stationName) {
        this(null, stationName);
    }

    public Station(final Long id, final String stationName) {
        this.id = id;
        this.stationName = new StationName(stationName);
    }

    public Long getId() {
        return id;
    }

    public StationName getStationName() {
        return stationName;
    }

    public String getStationNameValue() {
        return stationName.getStationName();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Station station = (Station) other;
        return Objects.equals(id, station.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + stationName + '\'' +
                '}';
    }
}
