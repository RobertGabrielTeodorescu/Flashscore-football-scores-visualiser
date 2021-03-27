package validator;

import entity.Team;

public class TeamValidator {
    public static void validateAddNewTeamFlow(Team team){
        if (team==null || team.getName()==null){
            throw new IllegalArgumentException("Team is null or name empty.");
        }
        if (team.getMatches_played()<(team.getWins()+team.getDraws()+team.getLosses())){
            throw new IllegalArgumentException("Not enough mathces played");
        }
    }
}
