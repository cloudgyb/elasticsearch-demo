package com.gyb.elasticsearch.demo.repository;

import com.gyb.elasticsearch.demo.entity.db.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author geng
 * 2020/12/18
 */
public interface BookRepository extends JpaRepository<Book,Integer> {
}
