package CryptOptima.server.domain.survey.repository;

import CryptOptima.server.domain.survey.dto.SurveyDto;
import CryptOptima.server.domain.survey.entity.Survey;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static CryptOptima.server.domain.survey.entity.QSurvey.survey;

@Repository
@RequiredArgsConstructor
public class QSurveyRepository {
    private final JPAQueryFactory jpaQueryFactory;


    // 1. group에 대한 사용자 수를 리턴한다.
    public List<SurveyDto.Group> findGroupResponses(SurveyDto.GroupCondition groupCond) {
        return jpaQueryFactory
                .select(Projections.fields(SurveyDto.Group.class,
                        survey.nation, survey.exchange, survey.type, survey.type.count().as("count")))
                .from(survey)
                .groupBy(exchangeGroup(groupCond).toArray(new Expression<?> [0]))
                .orderBy(survey.nation.asc(), survey.exchange.asc(), survey.type.asc()) // TODO SelectCond 중 순서를 부여해 정렬 조건 부여하기
                .fetch();
    }

    private List<Expression<?>> exchangeGroup(SurveyDto.GroupCondition groupCond) {
        List<Expression<?>> groupByExpressions = new ArrayList<>();

        if(groupCond.getNation())
            groupByExpressions.add(survey.nation);
        if(groupCond.getExchange())
            groupByExpressions.add(survey.exchange);
        if(groupCond.getType())
            groupByExpressions.add(survey.type);

        return groupByExpressions;
    }


    // 2. 조건에 해당하는 사용자 데이터를 리턴한다.
    public List<Survey> findSurveysWhere(SurveyDto.SelectCondition selectCond) {
        return jpaQueryFactory
                .selectFrom(survey)
                .where(nationEq(selectCond.getNation()), exchangeEq(selectCond.getExchange()), typeEq(selectCond.getType()))
                .orderBy(survey.nation.asc(), survey.exchange.asc(), survey.type.asc()) // TODO SelectCond 중 순서를 부여해 정렬 조건 부여하기
                .fetch();
    }

    private BooleanExpression nationEq(String nationCond) {
        return nationCond != null ? survey.nation.eq(nationCond) : null;
    }
    private BooleanExpression exchangeEq(String exchangeCond) {
        return exchangeCond != null ? survey.exchange.eq(exchangeCond) : null;
    }
    private BooleanExpression typeEq(String typeCond) {
        return typeCond != null ? survey.type.eq(typeCond) : null;
    }
}
