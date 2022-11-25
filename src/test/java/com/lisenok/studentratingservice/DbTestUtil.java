package com.lisenok.studentratingservice;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class DbTestUtil {

    private DbTestUtil() {}

    /**
     * Метод для ресета sequence
     * Получает запрос из метода getResetSqlTemplate, форматирует его (вставляет название таблицы) и выполняет
     * @param applicationContext - контекст приложения
     * @param tableNames - названия таблиц, где необходимо сделать ресет
     * @throws SQLException
     */
    public static void resetAutoIncrementColumns(ApplicationContext applicationContext,
                                                 String... tableNames) throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        String resetSqlTemplate = getResetSqlTemplate(applicationContext);
        try (Connection dbConnection = dataSource.getConnection()) {
            //Create SQL statements that reset the auto increment columns and invoke
            //the created SQL statements.
            for (String resetSqlArgument: tableNames) {
                try (Statement statement = dbConnection.createStatement()) {
                    String resetSql = String.format(resetSqlTemplate, resetSqlArgument);
                    statement.execute(resetSql);
                }
            }
        }
    }

    /**
     * Метод для формирования шаблона sql запроса на ресет sequence
     * Возвращает необходимый запрос из application.properties
     * @param applicationContext - контекст приложения
     * @return sql запрос на ресет sequence
     */
    private static String getResetSqlTemplate(ApplicationContext applicationContext) {
        //Read the SQL template from the properties file
        Environment environment = applicationContext.getBean(Environment.class);
        return environment.getRequiredProperty("test.reset.sql.template");
    }
}
