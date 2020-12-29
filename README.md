# Spring Boot + Elasticsearch 整合Demo

### 框架技术
* SpringBoot 2.3.7.RELEASE
* Elasticsearch 7.6.2
* MariaDB 10.1.37

### 项目配置
* Elasticsearch 配置
```properties
#非必须配置，默认值为localhost
es.host=localhost
#非必须配置，默认值9200
es.port=9200
```
* 数据库配置
在application.properties


### 启动项目
1. 首先启动Elasticsearch服务
```shell
 ${es_home}/bin/elasticsearch
```
>无需手动创建索引

2. 在数据库中创建表t_book
```mysql
create table t_book
(
	id int unsigned auto_increment primary key,
	title varchar(30) default ''  comment '书名',
	author varchar(20) default ''  comment '作者',
	price decimal(10,2) comment '价格',
	create_time datetime comment '创建时间',
	update_time datetime comment '更新时间'
);
```
3. 插入数据到数据库和ES
>执行com.gyb.elasticsearch.demo.ESBookOperationTests.testBatchAddBook()方法进行测试数据的插入

4. 启动项目
```shell
./mvnw spring-boot:run
```
5. 访问http://localhost:8080 进入首页进行书籍信息的添加和搜索