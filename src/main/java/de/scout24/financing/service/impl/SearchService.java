package de.scout24.financing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.Question;
import de.scout24.financing.solr.repository.QuestionSolrRepository;

@Service
public class SearchService extends BaseService{

    //TODO complete and return data
    public Page<Question> search(String term, Pageable pageable) {
        return serviceHelper.questionSolrRepository.findByTitleContainsOrTextContains(term, term, pageable);
    }
}
