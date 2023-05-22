package subway.domain.fare.discount;

import org.junit.jupiter.api.Test;
import subway.domain.fare.basic.DistanceFarePolicy;
import subway.domain.line.Fare;
import subway.domain.line.Line;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AgeGroupDiscountPolicyTest {

    List<Line> lines = List.of(new Line(1L, "1호선", 0));


    @Test
    void baby() {
        // given
        final Fare fare = new Fare(new DistanceFarePolicy(10, lines));

        // when
        final Fare discounted = fare.applyDiscount(new AgeGroupDiscountPolicy(5));

        // then
        assertThat(discounted).isEqualTo(new Fare(0));
    }

    @Test
    void child() {
        // given
        final Fare fare = new Fare(new DistanceFarePolicy(10, lines));

        // when
        final Fare discounted = fare.applyDiscount(new AgeGroupDiscountPolicy(6));

        // then
        assertThat(discounted).isEqualTo(new Fare(450));
    }

    @Test
    void teen() {
        // given
        final Fare fare = new Fare(new DistanceFarePolicy(10, lines));

        // when
        final Fare discounted = fare.applyDiscount(new AgeGroupDiscountPolicy(13));

        // then
        assertThat(discounted).isEqualTo(new Fare(720));
    }

    @Test
    void adult() {
        // given
        final Fare fare = new Fare(new DistanceFarePolicy(10, lines));

        // when
        final Fare discounted = fare.applyDiscount(new AgeGroupDiscountPolicy(20));

        // then
        assertThat(discounted).isEqualTo(new Fare(1250));
    }
}