package mappers;

import dto.LineupDto;
import entity.Lineup;
import utils.ApplicationUtils;

public class LineupMapper {

    public static LineupDto entityToDto(Lineup lineup){
        LineupDto lineupDto = new LineupDto();
        lineupDto.setId_lineup(lineup.getId_lineup());
        lineupDto.setId_match(lineup.getId_match());
        lineupDto.setId_team(lineup.getId_team());
        lineupDto.setFormation(lineup.getFormation());
        lineupDto.setStarting_players_ids(ApplicationUtils.stringToArraylist(lineup.getStarting_players_ids(), " "));
        lineupDto.setSubstitutes_ids(ApplicationUtils.stringToArraylist(lineup.getSubstitutes_ids(), " "));
        return lineupDto;
    }

    public static Lineup dtoToEntity(LineupDto lineupDto){
        Lineup lineup = new Lineup();
        lineup.setId_lineup(lineupDto.getId_lineup());
        lineup.setId_match(lineupDto.getId_match());
        lineup.setId_team(lineupDto.getId_team());
        lineup.setFormation(lineupDto.getFormation());
        lineup.setStarting_players_ids(ApplicationUtils.arrayListToString(lineupDto.getStarting_players_ids()));
        lineup.setSubstitutes_ids(ApplicationUtils.arrayListToString(lineupDto.getSubstitutes_ids()));
        return lineup;
    }
}
