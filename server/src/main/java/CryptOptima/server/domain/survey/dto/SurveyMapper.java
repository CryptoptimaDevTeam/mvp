package CryptOptima.server.domain.survey.dto;

import CryptOptima.server.domain.survey.entity.Survey;
import org.springframework.stereotype.Component;

@Component
public class SurveyMapper {

    public SurveyDto.Survey surveyToSurveyDto(Survey survey) {
        return SurveyDto.Survey.builder()
                .surveyId(survey.getSurveyId())
                .email(survey.getEmail())
                .ip(survey.getIp())
                .nation(survey.getNation())
                .exchange(survey.getExchange())
                .type(survey.getType())
                .build();
    }
}
