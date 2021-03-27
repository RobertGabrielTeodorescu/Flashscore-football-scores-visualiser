package mappers;

import dto.LeagueDto;
import entity.Leagues;

public class LeagueMapper {
    public static LeagueDto entityToDtoL(Leagues leagues){
        LeagueDto leagueDto = new LeagueDto();
        leagueDto.setId_league(leagues.getId_league());
        leagueDto.setName(leagues.getName());
        leagueDto.setNumber_of_teams(leagues.getNumber_of_teams());
        leagueDto.setCurrent_fixture(leagues.getCurrent_fixture());
        leagueDto.setTeams(leagues.getTeams());
        return leagueDto;
    }

    public static Leagues dtoToEntityL(LeagueDto leagueDto){
        Leagues leagues = new Leagues();
        leagues.setId_league(leagueDto.getId_league());
        leagues.setName(leagueDto.getName());
        leagues.setNumber_of_teams(leagueDto.getNumber_of_teams());
        leagues.setCurrent_fixture(leagueDto.getCurrent_fixture());
        leagues.setTeams(leagueDto.getTeams());
        return leagues;
    }

}
