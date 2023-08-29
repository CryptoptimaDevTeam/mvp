package CryptOptima.server.domain.survey.dto;

import lombok.*;

import java.util.List;

public class SurveyDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private String email;
        private String exchange;
        private String type;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupCondition { // Group By 조건
        private Boolean nation;
        private Boolean exchange;
        private Boolean type;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Group { // 그룹 정보 및 사용자 수
        private String nation;
        private String exchange;
        private String type;
        private Long count;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupResponse {
        private GroupCondition groupCondition; // group by 기준
        private List<Group> groupList; // group 별 데이터 & 해당 되는 사용자 수
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Survey { // 설문 데이터
        private Long surveyId;
        private String email;
        private String ip;
        private String nation;
        private String exchange;
        private String type;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SelectCondition { // where절 조건
        private String nation;
        private String exchange;
        private String type;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SelectResponse {
        private SelectCondition selectCondition;
        private List<Survey> surveyList;
    }

//    public static class Group {
//        private List<Survey> surveyList; // 그룹 내 사용자 데이터 목록
//        private int count; // 그룹 별 사용자 수
//    }
//
//    public static class Response {
//        private List<Group> groupList;
//    }
}
