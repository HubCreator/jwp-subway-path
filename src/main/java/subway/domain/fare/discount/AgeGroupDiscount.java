package subway.domain.fare.discount;

import subway.domain.line.Fare;

public class AgeGroupDiscount implements DiscountPolicy {

    private final AgeGroup ageGroup;

    public AgeGroupDiscount(int age) {
        ageGroup = AgeGroup.of(age);
    }

    @Override
    public Fare calculateDiscount(final int fare) {

        return ageGroup.calculate(fare);
    }
}
