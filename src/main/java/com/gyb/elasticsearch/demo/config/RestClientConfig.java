package com.gyb.elasticsearch.demo.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * ElasticSearch 客户端配置
 *
 * @author geng
 * 2020/12/19
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {
    @Value("${es.host:localhost}")
    private String esHost;
    @Value("${es.port:9200}")
    private Integer esPort;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(esHost+":"+esPort)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}