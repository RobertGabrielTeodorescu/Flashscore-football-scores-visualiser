package service;

import dto.LeagueDto;
import dto.TeamDto;
import dto.UserDto;
import entity.Leagues;
import entity.Team;
import entity.User;
import exception.CustomExceptionMessages;
import mappers.LeagueMapper;
import mappers.TeamMapper;
import mappers.UserMapper;
import repository.LeagueRepo;
import repository.UserRepo;
import utils.ApplicationUtils;
import validator.LeagueValidator;
import validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class LeagueService {
    private LeagueRepo leagueRepo;

    public LeagueService() {
        this.leagueRepo = new LeagueRepo();
    }

    public LeagueDto getLeague(String id_league) {

        if (id_league == null || id_league.equals("")) {
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_ID_MESSAGE);
        }
        //string 36 de caractere (UUID).
        //User user = userRepo.findUser(id);
        //if (user == null) {
        //   throw new EntityNotExistsException("No user having id " + id + " exists.");
        //}

        //return UserMapper.entityToDto(user);
        return null;
    }

    public void addLeague(LeagueDto leagueDto) {

        Leagues leagues= LeagueMapper.dtoToEntityL(leagueDto);
        LeagueValidator.validateAddNewLeagueFlow(leagues);
        leagues.setId_league(ApplicationUtils.generateUUID());
        leagueRepo.insertNewLeague(leagues);
    }

    public void removeLeague(LeagueDto leagueDto){
        Leagues leagues = LeagueMapper.dtoToEntityL(leagueDto);
        leagueRepo.deleteLeague(leagues);
    }

    public LeagueDto getLeagueById(String id_leagues){
        Leagues leagues= leagueRepo.findLeagueById(id_leagues);
        return LeagueMapper.entityToDtoL(leagues);
    }

    public void updateLeague(LeagueDto leagueDto){
        Leagues leagues = LeagueMapper.dtoToEntityL(leagueDto);
        leagueRepo.updateLeague(leagues);
    }

    public List<TeamDto> getTeamsOfLeague(String id_league){
        List<TeamDto> teamDtos=new ArrayList<>();
        List<Team> teams=leagueRepo.findTeamsOfLeague(id_league);
        for(Team team:teams){
            teamDtos.add(TeamMapper.entityToDto(team));
        }
        return teamDtos;
    }

    public List<LeagueDto> getLeagues(){
        List<LeagueDto> leagueDtos=new ArrayList<>();
        List<Leagues> leagues=leagueRepo.findAllLeagues();
        for(Leagues league:leagues){
            leagueDtos.add(LeagueMapper.entityToDtoL(league));
        }
        return leagueDtos;
    }

    public LeagueDto getLeagueByName(String name){
        Leagues leagues= leagueRepo.findLeagueByName(name);
        return LeagueMapper.entityToDtoL(leagues);
    }

}
