package mappers;

import dto.TeamDto;
import dto.UserDto;
import entity.Team;
import entity.User;
import utils.ApplicationUtils;

public class TeamMapper {
    public static TeamDto entityToDto(Team team){
        TeamDto teamDto = new TeamDto();
        teamDto.setId_team(team.getId_team());
        teamDto.setLeagues(team.getLeagues());
        teamDto.setId_followers(ApplicationUtils.stringToArraylist(team.getId_followers(), " "));
        teamDto.setName(team.getName());
        teamDto.setRank(team.getRank());
        teamDto.setMatches_played(team.getMatches_played());
        teamDto.setDraws(team.getDraws());
        teamDto.setLosses(team.getLosses());
        teamDto.setPoints(team.getPoints());
        teamDto.setCoach(team.getCoach());
        teamDto.setGoals(team.getGoals());
        teamDto.setWins(team.getWins());
        teamDto.setPlayers(team.getPlayers());
        teamDto.setHostMatches(team.getHostMatches());
        teamDto.setGuestMatches(team.getGuestMatches());
        return teamDto;
    }

    public static Team dtoToEntity(TeamDto teamDto){
        Team team=new Team();
        team.setId_team(teamDto.getId_team());
        team.setLeagues(teamDto.getLeagues());
        team.setId_followers(ApplicationUtils.arrayListToString(teamDto.getId_followers()));
        team.setName(teamDto.getName());
        team.setRank(teamDto.getRank());
        team.setMatches_played(teamDto.getMatches_played());
        team.setDraws(teamDto.getDraws());
        team.setLosses(teamDto.getLosses());
        team.setPoints(teamDto.getPoints());
        team.setCoach(teamDto.getCoach());
        team.setGoals(teamDto.getGoals());
        team.setWins(teamDto.getWins());
        team.setPlayers(teamDto.getPlayers());
        team.setHostMatches(teamDto.getHostMatches());
        team.setGuestMatches(teamDto.getGuestMatches());
        return team;
    }
}
