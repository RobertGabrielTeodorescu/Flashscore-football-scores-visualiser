package validator;

import entity.Lineup;

public class LineupValidator {

    public static void validateAddNewLineupFlow(Lineup lineup){

        if (lineup == null){
            throw new IllegalArgumentException("Lineup is null.");
        }
    }
}
