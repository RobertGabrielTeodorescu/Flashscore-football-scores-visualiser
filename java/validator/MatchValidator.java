package validator;

import entity.Match;

public class MatchValidator {

    public static void validateAddNewMatchFlow(Match match){

        if (match == null){
            throw new IllegalArgumentException("Match is null.");
        }

    }
}
