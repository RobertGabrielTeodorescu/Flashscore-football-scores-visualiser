package mappers;

import dto.StatisticDto;
import entity.Statistics;

public class StatisticMapper {
    public static StatisticDto entityToDtoS(Statistics statistic){
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setId_statistics(statistic.getId_statistics());
        statisticDto.setBall_possession(statistic.getBall_possession());
        statisticDto.setOffsides(statistic.getOffsides());
        statisticDto.setGoal_attempts(statistic.getGoal_attempts());
        statisticDto.setShots_on_goal(statistic.getShots_on_goal());
        statisticDto.setShots_off_goal(statistic.getShots_off_goal());
        statisticDto.setBlocked_shots(statistic.getBlocked_shots());
        statisticDto.setFree_kicks(statistic.getFree_kicks());
        statisticDto.setCorner_kicks(statistic.getCorner_kicks());
        statisticDto.setFoults(statistic.getFoults());
        statisticDto.setYellow_cards(statistic.getYellow_cards());
        statisticDto.setRed_cards(statistic.getRed_cards());
        statisticDto.setTotal_passes(statistic.getTotal_passes());
        statisticDto.setMatch(statistic.getMatch());
        return statisticDto;
    }

    public static Statistics dtoToEntityS(StatisticDto statisticDto){
        Statistics statistic = new Statistics();
        statistic.setId_statistics(statisticDto.getId_statistics());
        statistic.setBall_possession(statisticDto.getBall_possession());
        statistic.setOffsides(statisticDto.getOffsides());
        statistic.setGoal_attempts(statisticDto.getGoal_attempts());
        statistic.setShots_on_goal(statisticDto.getShots_on_goal());
        statistic.setShots_off_goal(statisticDto.getShots_off_goal());
        statistic.setBlocked_shots(statisticDto.getBlocked_shots());
        statistic.setFree_kicks(statisticDto.getFree_kicks());
        statistic.setCorner_kicks(statisticDto.getCorner_kicks());
        statistic.setFoults(statisticDto.getFoults());
        statistic.setYellow_cards(statisticDto.getYellow_cards());
        statistic.setRed_cards(statisticDto.getRed_cards());
        statistic.setTotal_passes(statisticDto.getTotal_passes());
        statistic.setMatch(statisticDto.getMatch());
        return statistic;
    }
}
