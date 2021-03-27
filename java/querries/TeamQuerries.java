package querries;

public class TeamQuerries {

    public static String FIND_TEAMS_BY_LEAGUE="FROM Team t WHERE t.id_league=: league_id ORDER BY t.team_rank";
    public static String FIND_TEAM_BY_NAME="From Team t WHERE t.name=:team_name";
    public static String FIND_ALL_TEAMS="FROM Team t ORDER BY t.name";

}
