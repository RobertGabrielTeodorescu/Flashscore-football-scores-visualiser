package mappers;

import dto.PlayerDto;
import entity.Player;

public class PlayerMapper {

    public static PlayerDto entityToDto(Player player){
        PlayerDto playerDto=new PlayerDto();
        playerDto.setAge(player.getAge());
        playerDto.setGoals_scored(player.getGoals_scored());
        playerDto.setId_player(player.getId_player());
        playerDto.setJersey_number(player.getJersey_number());
        playerDto.setMatches_played(player.getMatches_played());
        playerDto.setName(player.getName());
        playerDto.setTeam(player.getTeam());
        playerDto.setNationality(player.getNationality());
        playerDto.setPosition(player.getPosition());
        playerDto.setRed_cards(player.getRed_cards());
        playerDto.setYellow_cards(player.getYellow_cards());
        return playerDto;
    }

    public static Player dtoToEntity(PlayerDto playerDto){
        Player player=new Player();
        player.setAge(playerDto.getAge());
        player.setGoals_scored(playerDto.getGoals_scored());
        player.setId_player(playerDto.getId_player());
        player.setJersey_number(playerDto.getJersey_number());
        player.setMatches_played(playerDto.getMatches_played());
        player.setName(playerDto.getName());
        player.setTeam(playerDto.getTeam());
        player.setNationality(playerDto.getNationality());
        player.setPosition(playerDto.getPosition());
        player.setRed_cards(playerDto.getRed_cards());
        player.setYellow_cards(playerDto.getYellow_cards());
        return player;
    }

}
