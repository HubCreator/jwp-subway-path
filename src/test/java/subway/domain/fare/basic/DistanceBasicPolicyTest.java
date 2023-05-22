package subway.domain.fare.basic;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import subway.domain.line.Fare;
import subway.domain.line.Line;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DistanceBasicPolicyTest {

    // given
    List<Line> lines = List.of(new Line(1L, "1호선", 0));


    @Test
    void fare1() {
        // when
        final Fare fare = new Fare(new subway.domain.fare.basic.DistanceBasicPolicy(9, lines));

        // then
        assertThat(fare).isEqualTo(new Fare(1250));
    }

    @Test
    void fare2() {
        // when
        final Fare fare = new Fare(new subway.domain.fare.basic.DistanceBasicPolicy(12, lines));

        // then
        assertThat(fare).isEqualTo(new Fare(1350));
    }

    @Test
    void fare3() {
        // when
        final Fare fare = new Fare(new subway.domain.fare.basic.DistanceBasicPolicy(16, lines));

        // then
        assertThat(fare).isEqualTo(new Fare(1450));
    }

    @Test
    void fare4() {
        // when
        final Fare fare = new Fare(new subway.domain.fare.basic.DistanceBasicPolicy(55, lines));

        // then
        assertThat(fare).isEqualTo(new Fare(2150));
    }

    @Nested
    public class DistanceBasicPolicy {
        // given
        List<Line> lines = List.of(
                new Line(1L, "1호선", 1000),
                new Line(2L, "2호선", 500)

        );

        @ParameterizedTest
        @CsvSource(value = {
                "5,2250", "15,2350",
                "30,2650", "50,3050",
                "55,3150", "59,3250"
        })
        void fareTest(int distance, int finalFare) {
            // when
            final Fare fare = new Fare(new subway.domain.fare.basic.DistanceBasicPolicy(distance, lines));

            // then
            assertThat(fare).isEqualTo(new Fare(finalFare));
        }
    }
}