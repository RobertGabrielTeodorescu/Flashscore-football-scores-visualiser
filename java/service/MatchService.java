package service;

import dto.MatchDto;
import dto.TeamDto;
import entity.Match;
import entity.Team;
import exception.CustomExceptionMessages;
import exception.EntityNotExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mappers.MatchMapper;
import mappers.TeamMapper;
import repository.MatchRepo;
import utils.ApplicationUtils;
import validator.MatchValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MatchService {

    private MatchRepo matchRepo;

    public MatchService(){
        this.matchRepo = new MatchRepo();
    }

    public MatchDto findMatch(String id_match){
        if (id_match == null || id_match.equals("")){
            throw  new IllegalArgumentException(CustomExceptionMessages.INVALID_ID_MESSAGE);
        }

        Match match = matchRepo.findMatch(id_match);

        if (match == null){
            throw new EntityNotExistsException("No match having id " + id_match + " exists.");
        }

        return MatchMapper.entityToDto(match);
    }

    public void insertMatch(MatchDto matchDto){
        Match match = MatchMapper.dtoToEntity(matchDto);
        MatchValidator.validateAddNewMatchFlow(match);
        //match.setId_match(ApplicationUtils.generateUUID());
        matchRepo.insertMatch(match);
    }

    public void deleteMatch(MatchDto matchDto){
        Match match = matchRepo.findMatch(matchDto.getId_match());
        matchRepo.deleteMatch(match);
    }

    public void updateMatch(MatchDto matchDto){
        Match match = MatchMapper.dtoToEntity(matchDto);
        matchRepo.updateMatch(match);
    }

    public List<MatchDto> getResults(TeamDto teamDto){
        Team team= TeamMapper.dtoToEntity(teamDto);
        List<Match> results=matchRepo.findResults(team);
        List<MatchDto> dtoResults=new ArrayList<>();
        for(Match match:results){
            dtoResults.add(MatchMapper.entityToDto(match));
        }
        return dtoResults;
    }

    public List<MatchDto> getFixtures(TeamDto teamDto){
        Team team= TeamMapper.dtoToEntity(teamDto);
        List<Match> fixtures=matchRepo.findFixtures(team);
        List<MatchDto> dtoFixtures=new ArrayList<>();
        for(Match match:fixtures){
            dtoFixtures.add(MatchMapper.entityToDto(match));
        }
        return dtoFixtures;
    }

    public List<MatchDto> getMatches(){
        List<MatchDto> matchDtos=new ArrayList<>();
        List<Match> matches=matchRepo.getMatches();
        for(Match match:matches){
            matchDtos.add(MatchMapper.entityToDto(match));
        }
        return matchDtos;
    }


    public List<MatchDto> getFixturesByLeague(String id_league){

        List<Match> fixtures=matchRepo.findFixturesByLeague(id_league);
        List<MatchDto> matchDtos=new ArrayList<>();
        for(Match match:fixtures){
            matchDtos.add(MatchMapper.entityToDto(match));
        }
        return matchDtos;
    }

    public List<MatchDto> getResultsByLeague(String id_league){
        List<Match> results=matchRepo.findResultsByLeague(id_league);
        List<MatchDto> matchDtos=new ArrayList<>();
        for(Match match:results){
            matchDtos.add(MatchMapper.entityToDto(match));
        }
        return matchDtos;
    }

    public ObservableList<MatchDto> getAllMatches(boolean admin) {
        ObservableList<MatchDto> matches = FXCollections.observableArrayList();
        TeamService teamsService=new TeamService();
        for(MatchDto match: getMatches()){
            match.setHosts(match.getTeam1().getName());
            match.setGuests(match.getTeam2().getName());
            matches.add(match);
        }
        return matches;
    }

}
