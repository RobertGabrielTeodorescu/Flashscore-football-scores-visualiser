package controller;

import dto.MatchDto;
import dto.SummaryDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.MatchService;
import service.PlayerService;
import service.SummaryService;
import utils.ApplicationUtils;

public class AdminSummaryController {

    MatchDto matchDto;
    SummaryDto summaryDto;

    @FXML TextField player1TextField;
    @FXML TextField player2TextField;
    @FXML TextField minuteTextField;
    @FXML TextField typeTextField;
    @FXML TextField penaltyTextField;

    public void initialize(MatchDto matchDto){
        this.matchDto = matchDto;
    }

    public void addSummaryButton(ActionEvent actionEvent){
        PlayerService playerService = new PlayerService();
        summaryDto = new SummaryDto();
        summaryDto.setId_summary(ApplicationUtils.generateUUID());
        summaryDto.setId_match(matchDto.getId_match());

        String id_player1 = playerService.getPlayerByName(player1TextField.getText()).getId_player();

        String id_player2 = "null";
        if(!player2TextField.getText().equals("null")) {
            id_player2 = playerService.getPlayerByName(player2TextField.getText()).getId_player();
        }

        String id_team = playerService.getPlayerById(id_player1).getTeam().getId_team();

        summaryDto.setId_team(id_team);
        summaryDto.setId_player1(id_player1);
        summaryDto.setId_player2(id_player2);
        summaryDto.setMinute(minuteTextField.getText());
        summaryDto.setType(typeTextField.getText());
        summaryDto.setPenalty(penaltyTextField.getText());

        new SummaryService().addSummary(summaryDto);

        if(summaryDto.getType().equals("goal")){
            String result = matchDto.getResult();
            int goals1 = Integer.parseInt(result.split("-")[0]);
            int goals2 = Integer.parseInt(result.split("-")[1]);

            if(matchDto.getTeam1().getId_team().equals(summaryDto.getId_team())){
                goals1++;
            }
            else{
                goals2++;
            }

            matchDto.setResult(goals1 + "-" + goals2);
            new MatchService().updateMatch(matchDto);
        }
    }
}
