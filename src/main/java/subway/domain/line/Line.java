package subway.domain.line;

import subway.domain.section.DirectionStrategy;
import subway.domain.section.Distance;
import subway.domain.section.Section;
import subway.domain.section.Sections;
import subway.domain.station.Station;

import java.util.List;
import java.util.Objects;

public class Line {

    private Long id;
    private LineName lineName;
    private ExtraFare extraFare;
    private Sections sections;

    private Line() {
    }

    public Line(final String lineName, final Integer extraFare) {
        this(lineName, null, extraFare);
    }

    public Line(final String lineName, final Sections sections, final Integer extraFare) {
        this.lineName = new LineName(lineName);
        this.sections = sections;
        this.extraFare = new ExtraFare(extraFare);
    }

    public Line(final Long id, final String lineName, final Integer extraFare) {
        this(id, lineName, extraFare, null);
    }

    public Line(final Long id, final String lineName, final Integer extraFare, final Sections sections) {
        this.id = id;
        this.lineName = new LineName(lineName);
        this.extraFare = new ExtraFare(extraFare);
        this.sections = sections;
    }

    public void addSection(final Station existStation, final Station newStation, final DirectionStrategy directionStrategy, final Distance distance) {
        this.sections = sections.add(existStation, newStation, directionStrategy, distance);
    }

    public void delete(final Station station) {
        this.sections = sections.delete(station);
    }

    public Long getId() {
        return id;
    }

    public LineName getLineName() {
        return lineName;
    }

    public String getLineNameValue() {
        return lineName.getLineName();
    }

    public Sections getSections() {
        return sections;
    }

    public ExtraFare getExtraFare() {
        return extraFare;
    }

    public int getExtraFareValue() {
        return extraFare.getExtraFare();
    }

    public List<Section> sections() {
        return sections.getSections();
    }

    public List<Station> stations() {
        return sections.getOrderedStations();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Line line = (Line) other;
        return Objects.equals(id, line.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", name='" + lineName + '\'' +
                ", sections=" + sections +
                '}';
    }
}
