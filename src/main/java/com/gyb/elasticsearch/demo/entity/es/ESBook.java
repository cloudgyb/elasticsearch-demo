package com.gyb.elasticsearch.demo.entity.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author geng
 * 2020/12/18
 */
@Data
@Document(indexName = "book")
public class ESBook {
    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(type=FieldType.Text,analyzer="ik_max_word")
    private String title;
    @Field(type=FieldType.Text,analyzer="ik_max_word")
    private String author;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date createTime;
    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date updateTime;
}
