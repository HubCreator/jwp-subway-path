package subway.domain.fare.discount;

import subway.domain.line.Fare;

public interface DiscountPolicy {

    Fare calculateDiscount(int fare);
}
