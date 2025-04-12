package com.recycle.domain.question.repository;

import com.recycle.domain.question.document.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionEsRepository extends ElasticsearchRepository<Question, Long>, QuestionEsSearchRepository {
    // Custom query methods can be defined here
}
