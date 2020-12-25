package com.gyb.elasticsearch.demo.repository;

import com.gyb.elasticsearch.demo.entity.es.ESBook;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * ES Book repository
 *
 * @author geng
 * 2020/12/19
 */
public interface ESBookRepository extends ElasticsearchRepository<ESBook, String> {

    List<ESBook> findByTitleOrAuthor(String title, String author);

    @Highlight(fields = {
            @HighlightField(name = "title")
    })

    @Query("{\"match\":{\"title\":\"?0\"}}")
    SearchHits<ESBook> find(String keyword);
}
