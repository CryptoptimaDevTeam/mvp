package CryptOptima.server.domain.survey.service;

import CryptOptima.server.domain.survey.dto.SurveyDto;

import java.util.List;

public interface SurveyService {

    public SurveyDto.Survey createSurvey(SurveyDto.Create surveyCreateDto);
    public List<SurveyDto.Group> getGroupResponses(SurveyDto.GroupCondition groupCond);
//    public List<Tuple> getGroupResponses(SurveyDto.GroupCondition groupCond);
    public List<SurveyDto.Survey> getSurveysWhere(SurveyDto.SelectCondition selectDto);

}
