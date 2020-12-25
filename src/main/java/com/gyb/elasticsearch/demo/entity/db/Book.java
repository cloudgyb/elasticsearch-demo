package com.gyb.elasticsearch.demo.entity.db;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author geng
 * 2020/12/18
 */
@Data
@Entity
@Table(name = "t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private Double price;
    private Date createTime;
    private Date updateTime;
}
