package validator;

import entity.Summary;

public class SummaryValidator {
    public static void validateAddNewSummaryFlow(Summary summary){
        if(summary==null || summary.getId_match()==null){
            throw new IllegalArgumentException("Summary is null or no match");
        }
    }
}
