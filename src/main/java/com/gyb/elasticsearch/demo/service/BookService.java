package com.gyb.elasticsearch.demo.service;

import com.gyb.elasticsearch.demo.entity.db.Book;
import com.gyb.elasticsearch.demo.entity.es.ESBook;
import com.gyb.elasticsearch.demo.repository.BookRepository;
import com.gyb.elasticsearch.demo.repository.ESBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author geng
 * 2020/12/19
 */
@Slf4j
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ESBookRepository esBookRepository;
    private final TransactionTemplate transactionTemplate;

    public BookService(BookRepository bookRepository,
                       ESBookRepository esBookRepository,
                       TransactionTemplate transactionTemplate) {
        this.bookRepository = bookRepository;
        this.esBookRepository = esBookRepository;
        this.transactionTemplate = transactionTemplate;
    }

    public void addBook(Book book) {
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        final Book saveBook = transactionTemplate.execute((status) ->
                bookRepository.save(book)
        );
        final ESBook esBook = new ESBook();
        assert saveBook != null;
        BeanUtils.copyProperties(saveBook, esBook);
        esBook.setId(saveBook.getId() + "");
        try {
            esBookRepository.save(esBook);
        }catch (Exception e){
            log.error(String.format("保存ES错误！%s", e.getMessage()));
        }
    }

    public List<ESBook> searchBook(String keyword){
        return esBookRepository.findByTitleOrAuthor(keyword, keyword);
    }

    public SearchHits<ESBook> searchBook1(String keyword){
        return esBookRepository.find(keyword);
    }
}
