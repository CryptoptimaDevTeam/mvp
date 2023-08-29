package CryptOptima.server.domain.survey.service;

import CryptOptima.server.domain.survey.dto.SurveyDto;
import CryptOptima.server.domain.survey.dto.SurveyMapper;
import CryptOptima.server.domain.survey.entity.Survey;
import CryptOptima.server.domain.survey.repository.QSurveyRepository;
import CryptOptima.server.domain.survey.repository.SurveyRepository;
import CryptOptima.server.global.utils.IpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final QSurveyRepository qSurveyRepository;
    private final SurveyMapper surveyMapper;
    private final IpUtils ipUtils;

    @Override
    public SurveyDto.Survey createSurvey(SurveyDto.Create surveyCreateDto) {
        String ip = ipUtils.getIp();

        Survey survey = Survey.builder()
                .email(surveyCreateDto.getEmail())
                .ip(ip) //헤더에서 ip 추출
                .nation(ipUtils.getNationByIp(ip))
                .exchange(surveyCreateDto.getExchange())
                .type(surveyCreateDto.getType())
                .build();

        Survey savedSurvey = surveyRepository.save(survey);
        return surveyMapper.surveyToSurveyDto(savedSurvey);
    }

    // 1. group by 조건으로 해당 그룹 사용자 수를 추출한다.
    @Override
    public List<SurveyDto.Group> getGroupResponses(SurveyDto.GroupCondition groupCond) {
        return qSurveyRepository.findGroupResponses(groupCond);
    }

    // 2. where 조건에 해당하는 사용자 데이터를 추출한다.
    @Override
    public List<SurveyDto.Survey> getSurveysWhere(SurveyDto.SelectCondition selectDto) {
        // BooleanExpression 적용을 위해 DTO 객체를 repository단 까지 전달한다.
        // Service 단에서 풀면 null 분기에 따라 매개변수로 넘겨줘야 하기 때문에 번거롭다.
        List<Survey> surveys = qSurveyRepository.findSurveysWhere(selectDto);

        return surveys.stream().map(
                survey -> surveyMapper.surveyToSurveyDto(survey)
        ).collect(Collectors.toList());
    }

}
