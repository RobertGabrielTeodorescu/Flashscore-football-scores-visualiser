package querries;

public class SummaryQuerries {

    public static String FIND_BY_MATCH = "SELECT id_summary FROM flashscore.summary WHERE id_match='47abe150-451e-408b-acfb-4c93c878d055';";
    public static String FIND_SUMMARIES_BY_MATCH = "select id_summary from summary where id_match=?";

}
