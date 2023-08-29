package CryptOptima.server.domain.survey.repository;

import CryptOptima.server.domain.survey.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long>{

}
