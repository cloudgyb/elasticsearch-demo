package com.gyb.elasticsearch.demo;

import com.gyb.elasticsearch.demo.entity.db.Book;
import com.gyb.elasticsearch.demo.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DBBookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testFindAll(){
        final List<Book> all = bookRepository.findAll();
        all.forEach(System.out::println);
    }

    @Test
    void testAddBook(){
        final Book book = new Book();
        book.setTitle("C++ Primary plus");
        book.setAuthor("geng");
        book.setPrice(59.9);
        book.setCreateTime(new Date());
        final Book save = bookRepository.save(book);
        System.out.println(save);
    }

    @Test
    void testBatchAddBook(){
        final ArrayList<Book> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final Book book = new Book();
            book.setTitle("C++ Primary plus");
            book.setAuthor("geng");
            book.setPrice(59.9);
            book.setCreateTime(new Date());
            list.add(book);
        }
        final List<Book> books = bookRepository.saveAll(list);
        books.forEach(System.out::println);
    }

    @Test
    void testUpdateBook(){
        final Book book = new Book();
        book.setId(1);
        book.setTitle("C++ Primary plus");
        book.setAuthor("geng");
        book.setPrice(59.9);
        book.setUpdateTime(new Date());
        final Book save = bookRepository.save(book);
        System.out.println(save);
    }

    @Test
    void testUpdateBook2(){
        final Optional<Book> user = bookRepository.findById(1);
        final Book book = user.orElse(new Book());
        book.setUpdateTime(new Date());
        final Book save = bookRepository.save(book);
        System.out.println(save);
    }

}
