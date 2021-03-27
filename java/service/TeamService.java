package service;

import dto.TeamDto;
import dto.UserDto;
import entity.Team;
import entity.User;
import exception.CustomExceptionMessages;
import exception.EntityNotExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mappers.TeamMapper;
import mappers.UserMapper;
import repository.TeamRepo;
import repository.UserRepo;
import utils.ApplicationUtils;
import validator.TeamValidator;
import validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class TeamService {
    private TeamRepo teamRepo;

    public TeamService() {
        this.teamRepo = new TeamRepo();
    }

    public TeamDto findTeam(String id_team) {

        if (id_team == null || id_team.equals("")) {
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_ID_MESSAGE);
        }

        Team team = teamRepo.findTeam(id_team);
        if (team == null) {
           throw new EntityNotExistsException("No user having id " + id_team + " exists.");
        }

        return TeamMapper.entityToDto(team);

    }

    public void addTeam(TeamDto teamDto) {

        Team team= TeamMapper.dtoToEntity(teamDto);
        //team.setId_team(ApplicationUtils.generateUUID());
        TeamValidator.validateAddNewTeamFlow(team);
        teamRepo.insertNewTeam(team);
    }

    public void removeTeam(TeamDto teamDto){
        Team team=TeamMapper.dtoToEntity(teamDto);
        teamRepo.deleteTeam(team);
    }

    /*public TeamDto getTeamByName(String name){
        Team team= teamRepo.findTeamByName(name);
        return TeamMapper.entityToDto(team);
    }*/

    public void updateTeam(TeamDto teamDto){
        Team team=TeamMapper.dtoToEntity(teamDto);
        teamRepo.updateTeam(team);
    }

    public List<TeamDto> getTeamsByLeague(String id_league){
        List<TeamDto> teamDtos=new ArrayList<>();
        List<Team> teams=teamRepo.findTeamsByLeague(id_league);
        for(Team team:teams){
            teamDtos.add(TeamMapper.entityToDto(team));
        }
        return teamDtos;
    }

    public TeamDto getTeamByName(String name){
        Team team= teamRepo.findTeamByName(name);
        return TeamMapper.entityToDto(team);
    }

    public List<TeamDto> getAllTeams(){
        List<Team> teams=teamRepo.findAllTeams();
        List<TeamDto> teamDtos=new ArrayList<>();
        for(Team team:teams){
            teamDtos.add(TeamMapper.entityToDto(team));
        }
        return teamDtos;
    }

    public ObservableList<TeamDto> getMyTeams(UserDto u) {
        ObservableList<TeamDto> teams = FXCollections.observableArrayList();
        for(TeamDto team: getAllTeams()){
            if (team.getId_followers().contains(u.getId_user())){
                teams.add(team);
            }
        }
        return teams;
    }

}
