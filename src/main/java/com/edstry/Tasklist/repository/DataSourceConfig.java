package com.edstry.Tasklist.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/* Данная конфигурация позволяет получить из пула коннекшенов
 - коннекшен и с ним взаимодейстовать, это нужно спрингу
 чтобы обрабатывать транзакции */
@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final DataSource dataSource;
    public Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
}
