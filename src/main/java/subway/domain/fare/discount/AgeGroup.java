package subway.domain.fare.discount;

import subway.domain.line.Fare;

public enum AgeGroup {
    BABY {
        @Override
        public Fare calculate(final int fare) {
            return new Fare(0);
        }
    },
    CHILD {
        @Override
        public Fare calculate(final int fare) {
            return new Fare((int) Math.ceil((fare - 350) * 0.5));
        }
    },
    TEEN {
        @Override
        public Fare calculate(final int fare) {
            return new Fare((int) Math.ceil((fare - 350) * 0.8));
        }
    },
    ADULT {
        @Override
        public Fare calculate(final int fare) {
            return new Fare(fare);
        }
    };

    public static AgeGroup of(final int age) {
        if (age < 0) {
            throw new IllegalArgumentException("나이는 음수가 될 수 없습니다. 입력값: " + age);
        }
        if (age < 6) {
            return BABY;
        }
        if (age < 13) {
            return CHILD;
        }
        if (age < 19) {
            return TEEN;
        }
        return ADULT;
    }

    public abstract Fare calculate(final int fare);
}
