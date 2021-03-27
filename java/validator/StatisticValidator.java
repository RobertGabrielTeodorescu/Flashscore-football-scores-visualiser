package validator;

import entity.Statistics;

public class StatisticValidator {

    public static void validateAddNewStatisticFlow(Statistics statistic) {

        if (statistic == null || statistic.getId_statistics() == null) {
            throw new IllegalArgumentException("Statistic is null or name empty.");
        }


    }
}
