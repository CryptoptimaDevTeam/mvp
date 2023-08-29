package CryptOptima.server.domain.survey.controller;

import CryptOptima.server.domain.survey.dto.SurveyDto;
import CryptOptima.server.domain.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/server/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity createSurvey(@RequestBody SurveyDto.Create surveyCreateDto) {

        return new ResponseEntity(surveyService.createSurvey(surveyCreateDto), HttpStatus.OK);
    }

    @GetMapping("/groups") // TODO 필터링 조건을 RequestParam 으로 받을지, DTO로 받을지
    public ResponseEntity getSurveyGroups(@RequestBody SurveyDto.GroupCondition groupCond) {
        SurveyDto.GroupResponse response = SurveyDto.GroupResponse.builder()
                .groupCondition(groupCond) // group by 그룹핑 조건
                .groupList(surveyService.getGroupResponses(groupCond)) // group 별 해당 사용자 카운트 리스트
                .build();

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping // TODO 필터링 조건을 RequestParam 으로 받을지, DTO로 받을지
    public ResponseEntity getSurveysWhere(@RequestBody SurveyDto.SelectCondition selectCond) {

        SurveyDto.SelectResponse response = SurveyDto.SelectResponse.builder()
                .selectCondition(selectCond)
                .surveyList(surveyService.getSurveysWhere(selectCond))
                .build();

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
