package service;

import dto.LineupDto;
import dto.PlayerDto;
import entity.Lineup;
import exception.CustomExceptionMessages;
import exception.EntityNotExistsException;
import mappers.LineupMapper;
import repository.LineupRepo;
import utils.ApplicationUtils;
import validator.LineupValidator;

import java.util.ArrayList;
import java.util.List;

public class LineupService {

    private LineupRepo lineupRepo;

    public LineupService(){
        this.lineupRepo = new LineupRepo();
    }

    public LineupDto findLineup(String id_lineup){
        if (id_lineup == null || id_lineup.equals("")){
            throw  new IllegalArgumentException(CustomExceptionMessages.INVALID_ID_MESSAGE);
        }

        Lineup lineup =lineupRepo.findLineup(id_lineup);

        if (lineup == null){
            throw new EntityNotExistsException("No lineup having id " + id_lineup + " exists.");
        }

        return LineupMapper.entityToDto(lineup);
    }

    public List<LineupDto> getLineups(String id_match){
        List<String> id_lineups = lineupRepo.getLineups(id_match);
        List<LineupDto> lineupDto = new ArrayList<>();

        for(String id_lineup: id_lineups){
            lineupDto.add(LineupMapper.entityToDto(lineupRepo.findLineup(id_lineup)));
        }

        return lineupDto;
    }

    public void insertLineup(LineupDto lineupDto){
        Lineup lineup = LineupMapper.dtoToEntity(lineupDto);
        LineupValidator.validateAddNewLineupFlow(lineup);
        lineup.setId_lineup(ApplicationUtils.generateUUID());
        lineupRepo.insertLineup(lineup);
    }

    public void deleteLineup(LineupDto lineupDto){
        Lineup lineup = lineupRepo.findLineup(lineupDto.getId_lineup());
        lineupRepo.deleteLineup(lineup);
    }

    public void updateLineup(LineupDto lineupDto){
        Lineup lineup = LineupMapper.dtoToEntity(lineupDto);
        lineupRepo.updateLineup(lineup);
    }

    public String getPlayersData1(ArrayList<String> players_ids, PlayerService playerService){
        StringBuilder data = new StringBuilder();
        System.out.println(players_ids);
        players_ids.remove(0);
        for(String player_id: players_ids){
            PlayerDto playerDto = playerService.getPlayerById(player_id);
            data.append(playerDto.getJersey_number()).append(" ");
            if(playerDto.getJersey_number() < 10){
                data.append(" ");
            }
            data.append(playerDto.getName()).append(" (").append(playerDto.getNationality()).append(")\n");
        }
        return data.toString();
    }

    public String getPlayersData2(ArrayList<String> players_ids, PlayerService playerService){
        StringBuilder data = new StringBuilder();
        int maxLength = -1;
        ArrayList<String> items = new ArrayList<>();
        System.out.println(players_ids);
        players_ids.remove(0);
        for(String player_id: players_ids){
            PlayerDto playerDto = playerService.getPlayerById(player_id);
            String item = "(" + playerDto.getNationality() + ") " + playerDto.getName() + " ";
            if(playerDto.getJersey_number() < 10){
                item += " ";
            }
            item += playerDto.getJersey_number();
            if(item.length() > maxLength){
                maxLength = item.length();
            }
            items.add(item);
        }

        for(String item: items){
            for(int i=0; i<=maxLength-item.length(); i++){
                data.append("  ");
            }
            data.append(item).append("\n");
        }

        return data.toString();
    }



}
