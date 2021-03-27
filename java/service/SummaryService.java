package service;

import dto.SummaryDto;
import entity.Summary;
import exception.CustomExceptionMessages;
import exception.EntityNotExistsException;
import mappers.SummaryMapper;
import repository.SummaryRepo;
import utils.ApplicationUtils;
import validator.SummaryValidator;

import java.util.ArrayList;
import java.util.List;

public class SummaryService {
    private SummaryRepo summaryRepo;

    public SummaryService() {
        this.summaryRepo = new SummaryRepo();
    }

    public SummaryDto getSummary(String id_summary) {

        if (id_summary == null || id_summary.equals("")) {
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_ID_MESSAGE);
        }

        Summary summary = summaryRepo.findSummary(id_summary);
        if (summary == null) {
            throw new EntityNotExistsException("No user having id " + id_summary + " exists.");
        }
        return SummaryMapper.entityToDto(summary);

    }

    public void addSummary(SummaryDto summaryDto) {

        Summary summary= SummaryMapper.dtoToEntity(summaryDto);
        summary.setId_summary(ApplicationUtils.generateUUID());
        SummaryValidator.validateAddNewSummaryFlow(summary);
        summaryRepo.insertNewSummary(summary);
    }

    public void removeSummary(SummaryDto summaryDto){
        Summary summary=SummaryMapper.dtoToEntity(summaryDto);
        summaryRepo.deleteSummary(summary);
    }

    /*public UserDto getUserByName(String username){
        User user= userRepo.findUserByName(username);
        return UserMapper.entityToDto(user);
    }*/

    public void updateSummary(SummaryDto summaryDto){
        Summary summary=SummaryMapper.dtoToEntity(summaryDto);
        summaryRepo.updateSummary(summary);
    }

    public List<SummaryDto> getSummaries(String id_match){
        List<String> id_summaries = summaryRepo.getSummaries(id_match);
        List<SummaryDto> summariesDto = new ArrayList<>();

        for(String id_summary: id_summaries){
            summariesDto.add(SummaryMapper.entityToDto(summaryRepo.findSummary(id_summary)));
        }

        return summariesDto;
    }
}
