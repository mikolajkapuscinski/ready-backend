package ai.ready.ready.user.expPoints;


import ai.ready.ready.user.User;

public record Level(int level, double progression) {

    static final int BASE_PROMOTION_THRESHOLD = 100;
    static final double PROMOTION_THRESHOLD_MULTIPLIER = 1.2;

    public static Level getUserLevel(User user) {
        Long expPoints = user.getExpPoints();
        if (expPoints == null) return new Level(1, 0);
        int level = 1;
        double progression;
        double previousPromotionThreshold = 0;
        double currentPromotionThreshold = BASE_PROMOTION_THRESHOLD;
        while(currentPromotionThreshold <= expPoints) {
            level++;
            previousPromotionThreshold = currentPromotionThreshold;
            currentPromotionThreshold += currentPromotionThreshold * PROMOTION_THRESHOLD_MULTIPLIER;
        }

        progression = (expPoints - previousPromotionThreshold)/(currentPromotionThreshold - previousPromotionThreshold);

        return new Level(level, progression);
    }
}
