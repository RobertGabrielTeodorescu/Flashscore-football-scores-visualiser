package mappers;

import dto.SummaryDto;

import entity.Summary;

public class SummaryMapper {
    public static SummaryDto entityToDto(Summary summary){
        SummaryDto summaryDto = new SummaryDto();
        summaryDto.setId_summary(summary.getId_summary());
        summaryDto.setId_match(summary.getId_match());
        summaryDto.setId_team(summary.getId_team());
        summaryDto.setId_player1(summary.getId_player1());
        summaryDto.setId_player2(summary.getId_player2());
        summaryDto.setMinute(summary.getMinute());
        summaryDto.setType(summary.getType());
        summaryDto.setPenalty(summary.getPenalty());

        return summaryDto;
    }

    public static Summary dtoToEntity(SummaryDto summaryDto){
        Summary summary=new Summary();
        summary.setId_summary(summaryDto.getId_summary());
        summary.setId_match(summaryDto.getId_match());
        summary.setId_team(summaryDto.getId_team());
        summary.setId_player1(summaryDto.getId_player1());
        summary.setId_player2(summaryDto.getId_player2());
        summary.setMinute(summaryDto.getMinute());
        summary.setType(summaryDto.getType());
        summary.setPenalty(summaryDto.getPenalty());
        return summary;
    }
}
