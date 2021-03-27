package querries;

public class PlayerQuerries {

    public static String FIND_PLAYER_BY_NAME="FROM Player p WHERE p.name= :player_name ";
    public static String FIND_PLAYER_BY_ID="FROM Player p WHERE p.id_player= :player_id ";
    public static String FIND_PLAYERS_BY_TEAM="FROM Player p where p.id_team= :team_id";
    public static String FIND_ALL_PLAYERS="FROM Player p ORDER BY p.name";

}
