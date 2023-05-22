package subway.domain.fare;

import org.junit.jupiter.api.Test;
import subway.domain.fare.basic.DistanceFarePolicy;
import subway.domain.line.Fare;
import subway.domain.line.Line;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DistanceFarePolicyTest {

    // given
    List<Line> lines = List.of(new Line(1L, "1호선", 0));


    @Test
    void fare1() {
        // when
        final Fare fare = new Fare(new DistanceFarePolicy(9, lines));

        // then
        assertThat(fare).isEqualTo(new Fare(1250));
    }

    @Test
    void fare2() {
        // when
        final Fare fare = new Fare(new DistanceFarePolicy(12, lines));

        // then
        assertThat(fare).isEqualTo(new Fare(1350));
    }

    @Test
    void fare3() {
        // when
        final Fare fare = new Fare(new DistanceFarePolicy(16, lines));

        // then
        assertThat(fare).isEqualTo(new Fare(1450));
    }

    @Test
    void fare4() {
        // when
        final Fare fare = new Fare(new DistanceFarePolicy(58, lines));

        // then
        assertThat(fare).isEqualTo(new Fare(2150));
    }

}