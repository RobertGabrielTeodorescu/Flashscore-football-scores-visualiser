package querries;

public class MatchQuerries {

    public static String FIND_MATCHES_BY_TEAM_ID="FROM Match m WHERE m.id_team1=:team_id or m.id_team2=:team_id";
    public static String FIND_ALL_MATCHES="FROM Match m";
    public static String FIND_MATCHES_BY_LEAGUE="FROM Match m JOIN Team t where m.id_team1=t.id_team and t.id_league=:league_id";

}
