package de.scout24.financing.solr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

import de.scout24.financing.domain.Question;

public interface QuestionSolrRepository extends SolrCrudRepository<Question, Long> {
    Page<Question> findByTitleContainsOrTextContains(String title, String description, Pageable pageable);
}
