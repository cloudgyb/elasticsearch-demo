package com.gyb.elasticsearch.demo;

import com.gyb.elasticsearch.demo.entity.es.ESBook;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class ESBookOperationTests {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    void contextLoads() {
    }

    @Test
    void testFindAll(){
    }

    @Test
    void testAddBook(){
        final ESBook book = new ESBook();
        book.setTitle("ElasticSearch 权威指南");
        book.setAuthor("geng");
        book.setPrice(59.9);
        book.setCreateTime(new Date());
        final ESBook save = elasticsearchOperations.save(book);
        System.out.println(save);
    }

    @Test
    void testBatchAddBook(){
        final ArrayList<ESBook> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final ESBook book = new ESBook();
            book.setId(i+"");
            book.setTitle("Java 从入门到放弃"+i);
            book.setAuthor("geng");
            book.setPrice(59.9);
            book.setCreateTime(new Date());
            list.add(book);
        }
        Iterable<ESBook> books = elasticsearchOperations.save(list);
        books.forEach(System.out::println);
    }

    @Test
    void testUpdateBook(){
        final ESBook book = new ESBook();
        book.setId("1");
        book.setTitle("Java 从入门到放弃");
        book.setAuthor("geng");
        book.setPrice(59.9);
        book.setUpdateTime(new Date());
        final ESBook save = elasticsearchOperations.save(book);
        System.out.println(save);
    }

    @Test
    void testQuery(){
        final Criteria criteria = new Criteria("title").is("从入门到放弃");
        CriteriaQuery query = new CriteriaQuery(criteria);
        final SearchHits<ESBook> search = elasticsearchOperations.search(query, ESBook.class);
        search.forEach(System.out::println);
    }

    @Test
    void testNativeQuery(){
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("title", "C++"))
                .build();

        final SearchHits<ESBook> search = elasticsearchOperations.search(query, ESBook.class);
        search.forEach(System.out::println);
    }


}
