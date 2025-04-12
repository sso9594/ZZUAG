package com.recycle.domain.question.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.recycle.domain.question.document.Question;
import com.recycle.domain.question.dto.QuestionIdResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHits;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QuestionEsSearchRepositoryImpl implements QuestionEsSearchRepository {

    private final ElasticsearchOperations elasticsearchTemplate;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    public QuestionIdResult searchQuestionIdsByKeyword(String keyword, Pageable pageable) throws IOException {
        try {
            SearchResponse<Void> response = elasticsearchClient.search(s -> s
                            .index("question-search")
                            .query(q -> q
                                    .multiMatch(m -> m
                                            .query(keyword)
                                            .fields("title", "content")
                                    )
                            )
                            .from((int) pageable.getOffset())
                            .size(pageable.getPageSize())
                            .sort(sort -> sort
                                    .field(f -> f
                                            .field("createdAt")
                                            .order(co.elastic.clients.elasticsearch._types.SortOrder.Desc)
                                    )
                            )
                            .source(src -> src.filter(f -> f.includes(List.of())))
                    , Void.class);

            List<Long> ids = response.hits().hits().stream()
                    .map(Hit::id).filter(Objects::nonNull)
                    .map(Long::parseLong)
                    .toList();

            long total = response.hits().total() != null ? response.hits().total().value() : 0;

            return QuestionIdResult.builder()
                    .ids(ids)
                    .totalCount(total)
                    .build();

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

//    @Override
//    @Deprecated
//    public QuestionIdResult searchQuestionIdsByKeyword(String keyword, Pageable pageable) {
//        NativeQuery query = NativeQuery.builder()
//                .withQuery(Query.of(q -> q
//                        .multiMatch(m -> m
//                                .query(keyword)
//                                .fields("title", "content")
//                        )
//                ))
//                .withPageable(pageable)
//                .build();
//
//        SearchHits<Question> hits = elasticsearchTemplate.search(query, Question.class);
//
//        List<Long> ids = hits.stream()
//                .map(hit -> Long.valueOf(hit.getId()))
//                .toList();
//
//        long totalCount = hits.getTotalHits();
//
//        return QuestionIdResult.builder()
//                .ids(ids)
//                .totalCount(totalCount)
//                .build();
//    }


}

