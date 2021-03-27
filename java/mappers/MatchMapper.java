package mappers;

import dto.MatchDto;
import entity.Match;

public class MatchMapper {

    public static MatchDto entityToDto(Match match){
        MatchDto matchDto =  new MatchDto();
        matchDto.setId_match(match.getId_match());
        matchDto.setStatistics(match.getStatistics());
        matchDto.setTeam1(match.getTeam1());
        matchDto.setTeam2(match.getTeam2());
        matchDto.setTime(match.getTime());
        matchDto.setStadium(match.getStadium());
        matchDto.setReferee(match.getReferee());
        matchDto.setResult(match.getResult());
        return matchDto;
    }

    public static Match dtoToEntity(MatchDto matchDto){
        Match match = new Match();
        match.setId_match(matchDto.getId_match());
        match.setStatistics(matchDto.getStatistics());
        match.setTeam1(matchDto.getTeam1());
        match.setTeam2(matchDto.getTeam2());
        match.setTime(matchDto.getTime());
        match.setStadium(matchDto.getStadium());
        match.setReferee(matchDto.getReferee());
        match.setResult(matchDto.getResult());
        return match;
    }
}
