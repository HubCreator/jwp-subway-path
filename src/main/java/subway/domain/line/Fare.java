package subway.domain.line;

import subway.domain.fare.basic.BasicFarePolicy;
import subway.domain.fare.discount.DiscountPolicy;

import java.util.Objects;

public class Fare {

    private final int fare;

    public Fare(final BasicFarePolicy basicFarePolicy) {
        this.fare = basicFarePolicy.calculateFare();
    }

    public Fare(final int fare) {
        this.fare = fare;
    }

    public Fare applyDiscount(final DiscountPolicy discountPolicy) {

        return discountPolicy.calculateDiscount(fare);
    }

    public int getFare() {
        return fare;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Fare fare1 = (Fare) other;
        return fare == fare1.fare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fare);
    }

    @Override
    public String toString() {
        return "Fare{" +
                "fare=" + fare +
                '}';
    }
}
