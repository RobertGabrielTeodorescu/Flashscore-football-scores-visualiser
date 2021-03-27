package service;

import dto.PlayerDto;
import dto.TeamDto;
import entity.Match;
import entity.Player;
import entity.Team;
import exception.CustomExceptionMessages;
import exception.CustomPlayerExceptionMessages;
import exception.EntityNotExistsException;
import mappers.MatchMapper;
import mappers.PlayerMapper;
import mappers.TeamMapper;
import repository.PlayerRepo;
import repository.UserRepo;
import utils.ApplicationUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    private PlayerRepo playerRepo;

    public PlayerService(){
        this.playerRepo=new PlayerRepo();
    }

    public PlayerDto getPlayerById(String id){
        if(id==null||id.equals("")){
            throw new IllegalArgumentException(CustomPlayerExceptionMessages.INVALID_ID_MESSAGE);
        }
        Player player=playerRepo.findPlayerById(id);
        if(player==null){
            throw new EntityNotExistsException("No player having id "+id+" exists.");
        }
        return PlayerMapper.entityToDto(player);
    }

    public PlayerDto getPlayerByName(String name){
        if(name==null||name.equals("")){
            throw new IllegalArgumentException(CustomPlayerExceptionMessages.INVALID_NAME_MESSAGE);
        }
        Player player=playerRepo.findPlayerByName(name);
        if(player==null){
            throw new EntityNotExistsException("No player having name "+name+" exists.");
        }
        return PlayerMapper.entityToDto(player);
    }

    public void addPlayer(PlayerDto playerDto){
        //playerDto.setId_player(ApplicationUtils.generateUUID());
        Player player=PlayerMapper.dtoToEntity(playerDto);
        playerRepo.insertNewPlayer(player);
    }

    public void removePlayer(PlayerDto playerDto){
        Player player=PlayerMapper.dtoToEntity(playerDto);
        playerRepo.deletePlayer(player);
    }

    public void updateAge(PlayerDto playerDto){
        playerDto.setAge(playerDto.getAge()+1);
        Player player=PlayerMapper.dtoToEntity(playerDto);
        playerRepo.updatePlayer(player);
    }

    public void updateJerseyNumber(PlayerDto playerDto,int newNumber){
        if(newNumber<1||newNumber>99){
            throw new IllegalArgumentException(CustomPlayerExceptionMessages.INVALID_JERSEY_NUMBER_MESSAGE);
        }
        playerDto.setJersey_number(newNumber);
        Player player=PlayerMapper.dtoToEntity(playerDto);
        playerRepo.updatePlayer(player);
    }

    public void updateMatchesPlayed(PlayerDto playerDto){
        playerDto.setMatches_played(playerDto.getMatches_played()+1);
        Player player=PlayerMapper.dtoToEntity(playerDto);
        playerRepo.updatePlayer(player);
    }

    public void updateGoalsScored(PlayerDto playerDto,int goalsScored){
        if(goalsScored<=0){
            throw new IllegalArgumentException(CustomPlayerExceptionMessages.INVALID_GOALS_SCORED_MESSAGE);
        }
        playerDto.setGoals_scored(playerDto.getGoals_scored()+goalsScored);
        Player player=PlayerMapper.dtoToEntity(playerDto);
        playerRepo.updatePlayer(player);
    }

    public void updateYellowCards(PlayerDto playerDto,int newYellowCards){
        if(newYellowCards<=0){
            throw new IllegalArgumentException(CustomPlayerExceptionMessages.INVALID_YELLOW_CARDS_MESSAGE);
        }
        playerDto.setYellow_cards(playerDto.getYellow_cards()+newYellowCards);
        Player player=PlayerMapper.dtoToEntity(playerDto);
        playerRepo.updatePlayer(player);
    }

    public void updateRedCards(PlayerDto playerDto,int newRedCards){
        if(newRedCards<=0){
            throw new IllegalArgumentException(CustomPlayerExceptionMessages.INVALID_RED_CARDS_MESSAGE);
        }
        playerDto.setRed_cards(playerDto.getRed_cards()+newRedCards);
        Player player=PlayerMapper.dtoToEntity(playerDto);
        playerRepo.updatePlayer(player);
    }

    public void updatePlayer(PlayerDto playerDto){
        Player player = PlayerMapper.dtoToEntity(playerDto);
        playerRepo.updatePlayer(player);
    }

    public List<PlayerDto> getPlayersByTeam(TeamDto teamDto){
        List<PlayerDto> playerDtos=new ArrayList<>();
        Team team= TeamMapper.dtoToEntity(teamDto);
        for(Player player:playerRepo.findPlayersByTeam(team)){
            playerDtos.add(PlayerMapper.entityToDto(player));
        }
        return playerDtos;
    }

    public List<PlayerDto> getAllPlayers(){
        List<PlayerDto> playerDtos=new ArrayList<>();
        for(Player player:playerRepo.findAllPlayers()){
            playerDtos.add(PlayerMapper.entityToDto(player));
        }
        return playerDtos;
    }

}
