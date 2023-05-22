package subway.domain.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import subway.domain.fare.basic.DistanceFarePolicy;
import subway.domain.line.Fare;
import subway.domain.line.Line;
import subway.domain.section.Distance;
import subway.domain.section.Section;
import subway.domain.section.Sections;
import subway.domain.station.Station;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SubwayGraphTest {

    private static final Station A = new Station(1L, "A");
    private static final Station B = new Station(2L, "B");
    private static final Station C = new Station(3L, "C");
    private static final Station D = new Station(4L, "D");
    private static final Station E = new Station(5L, "E");
    private static final Station F = new Station(6L, "F");
    private static final Station G = new Station(7L, "G");
    private static final Station H = new Station(8L, "H");


    @Nested
    class FindPathTest {

        // given
        final List<Line> lines = List.of(
                new Line(1L, "1호선", 0, new Sections(
                        List.of(
                                new Section(1L, A, B, new Distance(3)),
                                new Section(2L, B, C, new Distance(4)),
                                new Section(3L, C, D, new Distance(5)),
                                new Section(4L, D, E, new Distance(5))

                        )
                )),
                new Line(2L, "2호선", 0, new Sections(
                        List.of(
                                new Section(5L, B, C, new Distance(3)),
                                new Section(6L, C, D, new Distance(1)),
                                new Section(7L, D, H, new Distance(5))
                        )
                ))
        );

        final SubwayGraph graph = SubwayGraph.of(lines);

        @DisplayName("A에서 H로 간다.")
        @Test
        void getShortestPath1() {
            // when
            final int distance = graph.getShortestPathDistance(A, H);
            final List<Station> stations = graph.getShortestPath(A, H);
            final Fare fare = new Fare(new DistanceFarePolicy(distance, lines));

            // then
            assertThat(distance).isEqualTo(12);
            assertThat(stations).containsExactly(A, B, C, D, H);
            assertThat(fare).isEqualTo(new Fare(1350));
        }

        @DisplayName("B에서 D로 간다.")
        @Test
        void getShortestPath2() {
            // when
            final int distance = graph.getShortestPathDistance(B, D);
            final List<Station> stations = graph.getShortestPath(B, D);
            final Fare fare = new Fare(new DistanceFarePolicy(distance, lines));

            // then
            assertThat(distance).isEqualTo(4);
            assertThat(stations).containsExactly(B, C, D);
            assertThat(fare).isEqualTo(new Fare(1250));
        }

        @DisplayName("B에서 E로 간다.")
        @Test
        void getShortestPath3() {
            // when
            final int distance = graph.getShortestPathDistance(B, E);
            final List<Station> stations = graph.getShortestPath(B, E);
            final Fare fare = new Fare(new DistanceFarePolicy(distance, lines));

            // then
            assertThat(distance).isEqualTo(9);
            assertThat(stations).containsExactly(B, C, D, E);
            assertThat(fare).isEqualTo(new Fare(1250));
        }

        @DisplayName("C에서 D로 간다.")
        @Test
        void getShortestPath4() {
            // when
            final int distance = graph.getShortestPathDistance(C, D);
            final List<Station> stations = graph.getShortestPath(C, D);
            final Fare fare = new Fare(new DistanceFarePolicy(distance, lines));

            // then
            assertThat(distance).isEqualTo(1);
            assertThat(stations).containsExactly(C, D);
            assertThat(fare).isEqualTo(new Fare(1250));
        }

        @DisplayName("H에서 A로 간다.")
        @Test
        void getShortestPath5() {
            // when
            final int distance = graph.getShortestPathDistance(H, A);
            final List<Station> stations = graph.getShortestPath(H, A);
            final Fare fare = new Fare(new DistanceFarePolicy(distance, lines));

            // then
            assertThat(distance).isEqualTo(12);
            assertThat(stations).containsExactly(H, D, C, B, A);
            assertThat(fare).isEqualTo(new Fare(1350));
        }

        @DisplayName("E에서 B로 간다.")
        @Test
        void getShortestPath6() {
            // when
            final int distance = graph.getShortestPathDistance(E, B);
            final List<Station> stations = graph.getShortestPath(E, B);
            final Fare fare = new Fare(new DistanceFarePolicy(distance, lines));

            // then
            assertThat(distance).isEqualTo(9);
            assertThat(stations).containsExactly(E, D, C, B);
            assertThat(fare).isEqualTo(new Fare(1250));
        }

        @DisplayName("E에서 E로 간다.")
        @Test
        void getShortestPath7() {
            // when
            final int distance = graph.getShortestPathDistance(E, E);
            final List<Station> stations = graph.getShortestPath(E, E);
            final Fare fare = new Fare(new DistanceFarePolicy(distance, lines));

            // then
            assertThat(distance).isEqualTo(0);
            assertThat(stations).containsExactly(E);
            assertThat(fare).isEqualTo(new Fare(1250));
        }
    }

    @Nested
    class FareTest {
        // given
        final List<Line> lines = List.of(
                new Line(1L, "1호선", 0, new Sections(
                        List.of(
                                new Section(1L, A, B, new Distance(18)),
                                new Section(2L, B, C, new Distance(19)),
                                new Section(3L, C, D, new Distance(20)),
                                new Section(4L, D, E, new Distance(20))

                        )
                )),
                new Line(2L, "2호선", 0, new Sections(
                        List.of(
                                new Section(5L, B, C, new Distance(18)),
                                new Section(6L, C, D, new Distance(16)),
                                new Section(7L, D, H, new Distance(20))
                        )
                ))
        );

        final SubwayGraph graph = SubwayGraph.of(lines);

        @Test
        void fare1() {
            // when
            final int distance = graph.getShortestPathDistance(A, H);
            final Fare fare = new Fare(new DistanceFarePolicy(distance, lines));

            // then
            assertThat(distance).isEqualTo(72);
            assertThat(fare).isEqualTo(new Fare(2250));
        }

        @Test
        void fare2() {
            // when


            // then
        }
    }
}
