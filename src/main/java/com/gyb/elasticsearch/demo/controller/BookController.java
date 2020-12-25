package com.gyb.elasticsearch.demo.controller;

import com.gyb.elasticsearch.demo.entity.db.Book;
import com.gyb.elasticsearch.demo.entity.es.ESBook;
import com.gyb.elasticsearch.demo.service.BookService;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geng
 * 2020/12/20
 */
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public Map<String,String> addBook(@RequestBody Book book){
        bookService.addBook(book);
        Map<String,String> map = new HashMap<>();
        map.put("msg","ok");
        return map;
    }

    @GetMapping("/book/search")
    public SearchHits<ESBook> search(String key){
        return bookService.searchBook1(key);
    }

}
