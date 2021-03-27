package validator;

import entity.Leagues;

public class LeagueValidator {

    public static void validateAddNewLeagueFlow(Leagues leagues) {

        if (leagues == null || leagues.getName() == null) {
            throw new IllegalArgumentException("League is null or name empty.");
        }


    }
}
