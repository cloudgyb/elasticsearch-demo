package com.gyb.elasticsearch.demo;

import com.gyb.elasticsearch.demo.entity.db.Book;
import com.gyb.elasticsearch.demo.entity.es.ESBook;
import com.gyb.elasticsearch.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ESBookServiceTests {

    @Autowired
    private BookService bookService;

    @Test
    void testAddBook(){
        final Book book = new Book();
        book.setTitle("离散数学1");
        book.setAuthor("geng");
        book.setPrice(56.0);
        book.setCreateTime(new Date());
        bookService.addBook(book);
    }

    @Test
    void testSearchBook(){
        final List<ESBook> books = bookService.searchBook("放弃");
        books.forEach(System.out::println);
    }

    @Test
    void testSearchBook2(){
        final SearchHits<ESBook> searchHits = bookService.searchBook1("C++");
        searchHits.forEach(System.out::println);
    }
}
