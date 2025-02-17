package ai.ready.ready.bookPossesion;

import lombok.Getter;

@Getter
public enum Rating {
    ZERO(0d),
    ZERO_AND_A_HALF(0.5),
    ONE(1d),
    ONE_AND_A_HALF(1.5),
    TWO(2d),
    TWO_AND_A_HALF(2.5),
    THREE(3d),
    THREE_AND_A_HALF(3.5),
    FOUR(4d),
    FOUR_AND_A_HALF(4.5),
    FIVE(5d);

    private final Double value;

    Rating(Double value) {
        this.value = value;
    }

}
