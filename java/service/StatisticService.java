package service;

import dto.StatisticDto;
import dto.UserDto;
import entity.Statistics;
import entity.User;
import exception.CustomExceptionMessages;
import mappers.StatisticMapper;
import mappers.UserMapper;
import repository.StatisticRepo;
import repository.UserRepo;
import utils.ApplicationUtils;
import validator.StatisticValidator;
import validator.UserValidator;

public class StatisticService {
    private StatisticRepo statisticRepo;

    public StatisticService() {
        this.statisticRepo = new StatisticRepo();
    }

    public StatisticDto getStatistic(String id_statistics) {

        if (id_statistics == null || id_statistics.equals("")) {
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

    public void addStatistic(StatisticDto statisticDto) {

        Statistics statistic = StatisticMapper.dtoToEntityS(statisticDto);
        StatisticValidator.validateAddNewStatisticFlow(statistic);
        statisticRepo.insertNewStatistic(statistic);
    }

    public void removeStatistic(StatisticDto statisticDto){
        Statistics statistic = StatisticMapper.dtoToEntityS(statisticDto);
        statisticRepo.deleteStatistic(statistic);
    }

    public StatisticDto getStatisticById(String id_statistic){
        Statistics statistic = statisticRepo.findStatisticById(id_statistic);
        return StatisticMapper.entityToDtoS(statistic);
    }

    public void updateStatistic(StatisticDto statisticDto){
        Statistics statistic = StatisticMapper.dtoToEntityS(statisticDto);
        statisticRepo.updateNewStatistic(statistic);
    }
}
